package Kingdom.Military;


import Resource.ResourceType;
import Kingdom.Treasury.Cost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Army {
    private final List<MilitaryUnit> units;
    private boolean isArmyInDebt = false;

    public Army(){
        this.units = new ArrayList<>();
    }
    public void addUnit(MilitaryUnit unit){
        this.units.add(unit);
    }
    public void removeUnit(MilitaryUnit unit){
        this.units.remove(unit);
    }
    public void removeDeadUnits(){
        System.out.println("Removing dead units from the army");

        int initialSize = this.units.size();
        this.units.removeIf(unit -> !unit.isAlive());
        int finalSize = this.units.size();
        int removedCount = initialSize - finalSize;

        if (removedCount > 0) {
            System.out.println("Total of "+ removedCount + " have been removed from the army");
        } else {
            System.out.println("No dead units have been found.");
        }
    }
    public double getTotalDamageOutput(){
        return this.units.stream()
                .mapToDouble(MilitaryUnit::calculateDamageOutput)
                .sum();

    }
    public Cost getTotalMaintenanceCost(){
        Map<ResourceType,Integer> totalCostMap = this.units.stream()
                .map(MilitaryUnit::getMaintenanceCost)
                .flatMap(cost -> cost.requiredResources().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ));
        return new Cost(totalCostMap);
    }
    public List<MilitaryUnit> getUnits() {
        return Collections.unmodifiableList(this.units);
    }
    public int getSize() {
        return this.units.size();
    }

    public boolean isArmyInDebt() {
        return isArmyInDebt;
    }

    public void setArmyInDebt(boolean armyInDebt) {
        isArmyInDebt = armyInDebt;
    }
}
