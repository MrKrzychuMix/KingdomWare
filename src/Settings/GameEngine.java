package Settings;

import Event.*;
import Kingdom.Building.*;
import Interface.IRecruitingBuilding;
import Kingdom.Kingdom;
import Kingdom.Military.Army;
import Kingdom.Military.MilitaryUnit;
import Kingdom.Military.Unit.UnitBlueprint;
import Kingdom.Military.Unit.UnitDataRegistry;
import Kingdom.Military.Unit.UnitFactory;
import Kingdom.Military.Unit.UnitType;
import Kingdom.Treasury.Cost;
import Resource.ResourceType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameEngine {
    private Kingdom kingdom;
    private final EventBus eventBus;
    private boolean wasUpkeepPaid = true;
    private int currentTurn = 1;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final RecruitmentCostCalculator costCalculator;

    public GameEngine(Kingdom kingdom, EventBus eventBus,RecruitmentCostCalculator costCalculator) {
        if (kingdom == null || eventBus == null) {
            throw new IllegalArgumentException("Kingdom i EventBus nie mogą być null.");
        }
        this.kingdom = kingdom;
        this.eventBus = eventBus;
        this.costCalculator = costCalculator;
    }
    private long countTotalBuildingsOfType(BuildingType type, Kingdom kingdom){
        Stream<Building> completedBuildingsStream = kingdom.getCompletedBuildings()
                .stream();

        Stream<Building> ongoingBuildingsStream = kingdom.getOngoingConstructions()
                .stream()
                .map(ConstructionJob::getBuilding);

        return Stream.concat(completedBuildingsStream, ongoingBuildingsStream)
                .filter(building -> building.getType() == type)
                .count();
    }

    public boolean canBuild(BuildingType type, Kingdom kingdom){
        if(type == BuildingType.SHOP){
            long existingShops = countTotalBuildingsOfType(BuildingType.SHOP,kingdom);
            if (existingShops > 0) {
                System.out.println("ERROR: You can build shop once");
                return false;
            }
        }
        if(type == BuildingType.WALL){
            long existingWalls = countTotalBuildingsOfType(BuildingType.WALL,kingdom);
            long existingCastles = countTotalBuildingsOfType(BuildingType.CASTLE,kingdom);
            if (existingCastles == 0) {
                System.out.println("ERROR: To build walls, you will have to build a castle first.");
                return false;
            }
            if (existingWalls >= existingCastles*4) {
                System.out.printf("ERROR: Wall maximum capacity reached (%d/%d). Build more castles to upradge it.%n",
                        existingWalls, existingCastles*4);
                    return false;
                }
        }
        if(type == BuildingType.GARDEN){
            long existingCastles = countTotalBuildingsOfType(BuildingType.CASTLE,kingdom);
            if (existingCastles == 0) {
                System.out.println("ERROR: To build garden, you will have to build a castle first.");
                return false;
            }
        }
        return true;
    }
    public RecruitmentResult recruitUnit(UnitType unitType) {
        Kingdom currentKingdom = this.kingdom;

        UnitBlueprint blueprint = UnitDataRegistry.getBlueprint(unitType);
        RecruitmentResult result;

        Cost initialCost;
        BuildingType requiredBuilding = blueprint.requiredBuilding();

        if(requiredBuilding != null) {
            long buildingCount = countTotalBuildingsOfType(requiredBuilding, currentKingdom);
            if (buildingCount > 0) {
                initialCost = blueprint.recruitmentCost();
            } else {
                if (blueprint.recruitableWithoutBuilding()) {
                    initialCost = blueprint.recruitmentCost().withIncreasedCost(20);
                } else {
                    result = RecruitmentResult.failure("ERROR: To recruit" + blueprint.name() + " you need " + requiredBuilding + ".");
                    this.eventBus.publish(result);
                    return result;
                }
            }
        } else {
            initialCost = blueprint.recruitmentCost();
        }
        Cost finalCost = costCalculator.calculateRecruitmentCost(unitType,initialCost);

        if(!currentKingdom.getTreasury().hasEnough(finalCost)){
            result = RecruitmentResult.failure("Insufficient resources. Required: "+ finalCost.requiredResources());
            return result;
        }
        currentKingdom.getTreasury().removeResource(finalCost);
        MilitaryUnit newUnit = UnitFactory.createUnit(unitType);
        currentKingdom.getArmy().addUnit(newUnit);

        result = RecruitmentResult.success("Successfully recruited: " + blueprint.name());
        this.eventBus.publish(result);
        return result;
    }
    public List<Building> getRecruitmentBuildings(Kingdom kingdom) {
        return kingdom.getCompletedBuildings().stream()
                .filter(building -> building.hasCategory(BuildingCategory.MILITARY))
                .collect(Collectors.toList());
    }
    public List<UnitBlueprint> getAvailableUnitsFor(Building building) {
        if (building instanceof IRecruitingBuilding) {
            IRecruitingBuilding recruitingBuilding = (IRecruitingBuilding) building;
            Set<UnitType> unitTypes = recruitingBuilding.getRecruitableUnits();
            return unitTypes.stream()
                    .map(UnitDataRegistry::getBlueprint)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    public void processPlayerRecruitmentChoice(UnitType chosenUnit, GameEngine engine, Kingdom kingdom) {
        RecruitmentResult result = engine.recruitUnit(chosenUnit);
        String messageToShow = result.message();
        System.out.println(messageToShow);
        if (!result.wasSuccessful()) {

        }
    }
    private void handleDesertion(Army army){
        if(army.getUnits().isEmpty()){
            System.out.println("Your army is empty!");
            return;
        }
        Double desertionPercentage = 0.10;
        double rawNumberOfDeserters = army.getSize() * desertionPercentage;
        int unitsToDesert = (int)Math.ceil(rawNumberOfDeserters);
        System.out.println("Due to insufficient pay "+ unitsToDesert + " are preparing to leave your army...");
        List<MilitaryUnit> unitsToChooseFrom = new ArrayList<>(army.getUnits());
        Collections.shuffle(unitsToChooseFrom);
        List<MilitaryUnit> deserters = new ArrayList<>(unitsToChooseFrom.subList(0,unitsToDesert));
        for(MilitaryUnit deserter : deserters){
            army.removeUnit(deserter);
            System.out.println(deserter.getName()+ "has left the army!");
        }
        System.out.println("Desertion is over");
    }
    public void handleBuildRequest(BuildingType type, Kingdom kingdom) {
        System.out.println("INFO: Build request received: " + type);
        if (!canBuild(type, kingdom)) {
            System.out.println("INFO: Build request denied due to regulations.");
            return;
        }
        BuildingBlueprint blueprint = BuildingDataRegistry.getBlueprint(type);
        Cost cost = blueprint.constructionCost();

        if (!kingdom.getTreasury().hasEnough(cost)) {
            System.out.println("ERROR: Build proccess aborted. Insufficient resources in the treasury.");
            System.out.println("Required: " + cost.requiredResources());
            System.out.println("Treasury: " + kingdom.getTreasury().getAllResourcesAsString());
            return;
        }

        System.out.println("INFO: Builders found. Building proccess has began...");

        kingdom.getTreasury().removeResource(cost);

        Building buildingToConstruct = BuildingFactory.createBuilding(type);
        kingdom.startConstruction(buildingToConstruct);
    }
    private void triggerRandomEvent() {
        EventCategory chosenCategory = EventCategory.getRandomCategory();
        System.out.println("DEBUG: Random event is: "+ chosenCategory);
        switch(chosenCategory){
            case RESOURCE_EVENT:
                resourceEvent(true);
                break;
            case NEGATIVE_RESOURCE_EVENT:
                resourceEvent(false);
                break;
            case COST_MODIFIER:
                createCostModifierEvent();
                break;
            case UNIT_DISCOUNT:
                createUnitCostModifierEvent();
                break;
            case NO_EVENT:
                System.out.println("DEBUG: No event");
                break;
            default:
                break;
        }
    }
    private void resourceEvent(boolean lucky){
        int amount;
        String message;
        ResourceType[] allResources = ResourceType.values();
        ResourceType chosenResource = allResources[random.nextInt(allResources.length)];
        if(lucky) {
            amount = 20 + random.nextInt(81);
            message = "Wow! You have found " + amount + " " + chosenResource + "!";
        } else {
            amount = 20 + random.nextInt(31);
            message = "Oh... someone has stole from you  " + amount + " " + chosenResource + "!";
        }
        eventBus.publish(new RandomResourceEvent(chosenResource, amount, message));
    }
    private void createUnitCostModifierEvent(){
        String message;
        int min = -80;
        int max = 100;
        int percentage = - min + random.nextInt(max+1);
        UnitType[] allUnits = UnitType.values();
        UnitType chosenUnit = allUnits[random.nextInt(allUnits.length)];
        if(percentage > 0){
            message = "Recruiting time! Day of "+chosenUnit+" has risen!";
        } else {
            message = "It looks like "+chosenUnit+" is overpriced today.";
        }
        eventBus.publish(new UnitCostChangeEvent(chosenUnit,percentage,message));
    }
    private void createCostModifierEvent() {
        String message;
        int min = -50;
        int max = 200;
        int percentage = - min + random.nextInt(max+1);
        int positivenumber = 100 - percentage;
        int negativenumber = percentage *(-1);
        if(percentage > 0){
            message = "Royal decree - every soldier is available today for "+ positivenumber +" its price";
            eventBus.publish(new CostModifier(percentage,message));
        } else {
            message = "Royal decree - military units price have been raised for "+ negativenumber+" its price";
            eventBus.publish(new CostModifier(percentage,message));
        }

    }

    public void nextTurn(){
        System.out.println(" --- TURN "+ currentTurn+" ---");
        triggerRandomEvent();
        // ECONOMY
        List<Building> completedBuildings = this.kingdom.getCompletedBuildings();
        for(Building buildings: completedBuildings) {
            buildings.produceResources(kingdom.getTreasury());
        }
        // MILITARY
        Cost totalUpkeep = kingdom.getArmy().getTotalMaintenanceCost();
        if(kingdom.getTreasury().hasEnough(totalUpkeep)){
            kingdom.getTreasury().removeResource(totalUpkeep);
            wasUpkeepPaid = true;
        } else {
            wasUpkeepPaid = false;
            System.out.println("You did not pay your soldiers required rent:" + totalUpkeep.requiredResources());
        }
        if(kingdom.getArmy().isArmyInDebt()){
            handleDesertion(kingdom.getArmy());
        }
        kingdom.processConstructionQueue();
        currentTurn++;
    }
}
