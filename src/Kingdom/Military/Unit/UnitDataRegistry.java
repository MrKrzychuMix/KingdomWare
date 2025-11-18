package Kingdom.Military.Unit;

import Kingdom.Building.BuildingType;
import Resource.ResourceType;
import Kingdom.Treasury.Cost;

import java.util.EnumMap;
import java.util.Map;

public class UnitDataRegistry {
    private static Map<UnitType, UnitBlueprint> blueprints = new EnumMap<>(UnitType.class);
    static {
        blueprints.put(UnitType.ARCHER, new UnitBlueprint(
                "Archer",
                3,
                1,
                0,
                2,
                Cost.of(ResourceType.MANPOWER,5,ResourceType.GOLD,10),
                Cost.of(ResourceType.GOLD,5,ResourceType.FOOD,5),
                50,
                BuildingType.SHOOTINGRANGE,
                true
        ));
        blueprints.put(UnitType.CROSSBOWMAN, new UnitBlueprint(
                "Crossbowman",
                12,
                5,
                0,
                3,
                Cost.of(ResourceType.MANPOWER,20,ResourceType.GOLD,50),
                Cost.of(ResourceType.GOLD,25,ResourceType.FOOD,20),
                100,
                BuildingType.SHOOTINGRANGE,
                false
        ));
        blueprints.put(UnitType.KNIGHT, new UnitBlueprint(
                "Knight",
                30,
                20,
                0,
                0,
                Cost.of(ResourceType.MANPOWER,50,ResourceType.GOLD,100),
                Cost.of(ResourceType.GOLD,50,ResourceType.FOOD,30),
                250,
                BuildingType.STABLE,
                false
        ));
        blueprints.put(UnitType.MAGE, new UnitBlueprint(
                "Mage",
                0,
                5,
                30,
                2,
                Cost.of(ResourceType.MANPOWER,20,ResourceType.GOLD,100),
                Cost.of(ResourceType.GOLD,30,ResourceType.SILVER,30),
                150,
                BuildingType.ACADEMY,
                false
        ));
        blueprints.put(UnitType.SWORDSMAN, new UnitBlueprint(
                "Swordsman",
                10,
                3,
                0,
                0,
                Cost.of(ResourceType.MANPOWER,5,ResourceType.GOLD,15),
                Cost.of(ResourceType.GOLD,5,ResourceType.FOOD,10),
                100,
                BuildingType.BARRACK,
                true
        ));
    }
    public static UnitBlueprint getBlueprint(UnitType type) {
        UnitBlueprint blueprint = blueprints.get(type);
        if (blueprint == null) {
            throw new IllegalArgumentException("Missing definition for this type of unit: " + type);
        }
        return blueprint;
    }
}
