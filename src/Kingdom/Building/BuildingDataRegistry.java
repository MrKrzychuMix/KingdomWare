package Kingdom.Building;

import Kingdom.Military.Unit.UnitBlueprint;
import Kingdom.Military.Unit.UnitType;
import Kingdom.Treasury.Cost;
import Resource.ResourceType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class BuildingDataRegistry {
    private static Map<BuildingType, BuildingBlueprint> blueprints = new EnumMap<>(BuildingType.class);
    static {
        blueprints.put(BuildingType.SHOP, new BuildingBlueprint(
                "Shop",
                Cost.of(ResourceType.WOOD,10),
                5,
                EnumSet.of(BuildingCategory.CIVILIAN,
                        BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.SHOP
        ));
        blueprints.put(BuildingType.HOUSE, new BuildingBlueprint(
                "House",
                Cost.of(ResourceType.WOOD, 10),
                3,
                EnumSet.of(BuildingCategory.CIVILIAN),
                Collections.emptySet(),
                BuildingType.HOUSE
        ));
        blueprints.put(BuildingType.TAVERN, new BuildingBlueprint(
                "Tavern",
                new Cost( Map.of(
                        ResourceType.WOOD,20,
                        ResourceType.STONE,5,
                        ResourceType.IRON,5,
                        ResourceType.GOLD,100
                )),
                8,
                EnumSet.of(BuildingCategory.CIVILIAN,
                        BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.TAVERN
        ));
        blueprints.put(BuildingType.CASTLE, new BuildingBlueprint(
                "Castle",
                new Cost(Map.of(
                        ResourceType.GOLD,500,
                        ResourceType.WOOD,500,
                        ResourceType.STONE,500,
                        ResourceType.IRON,500,
                        ResourceType.WATER,500
                )),
                200,
                EnumSet.of(BuildingCategory.DEFENSIVE,
                        BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.CASTLE
        ));

        blueprints.put(BuildingType.TOWER, new BuildingBlueprint(
                "Tower",
                Cost.of(ResourceType.WOOD,25,
                        ResourceType.STONE,50),
                25,
                EnumSet.of(BuildingCategory.DEFENSIVE),
                Collections.emptySet(),
                BuildingType.TOWER
        ));

        blueprints.put(BuildingType.WALL, new BuildingBlueprint(
                "Wall",
                Cost.of(ResourceType.STONE,100),
                35,
                EnumSet.of(BuildingCategory.DEFENSIVE),
                Collections.emptySet(),
                BuildingType.WALL
        ));

        blueprints.put(BuildingType.WATCHTOWER, new BuildingBlueprint(
                "Watchtower",
                Cost.of(ResourceType.WOOD,50,
                        ResourceType.STONE,5),
                15,
                EnumSet.of(BuildingCategory.CIVILIAN),
                Collections.emptySet(),
                BuildingType.WATCHTOWER
        ));
        blueprints.put(BuildingType.FARM, new BuildingBlueprint(
                "Farm",
                Cost.of(ResourceType.WOOD,5),
                5,
                EnumSet.of(BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.FARM
        ));
        blueprints.put(BuildingType.LUMBERMILL, new BuildingBlueprint(
                "LumberMill",
                Cost.of(ResourceType.WOOD,5),
                5,
                EnumSet.of(BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.LUMBERMILL
        ));
        blueprints.put(BuildingType.MINE, new BuildingBlueprint(
                "Mine",
                new Cost(Map.of(ResourceType.WOOD,15,
                        ResourceType.FOOD,30)),
                8,
                EnumSet.of(BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.MINE
        ));
        blueprints.put(BuildingType.WINDMILL, new BuildingBlueprint(
                "Windmill",
                new Cost(Map.of(
                        ResourceType.GOLD,50,
                        ResourceType.WOOD,50,
                        ResourceType.IRON,5
                )),
                10,
                EnumSet.of(BuildingCategory.ECONOMY),
                Collections.emptySet(),
                BuildingType.WINDMILL
        ));
        blueprints.put(BuildingType.ACADEMY, new BuildingBlueprint(
                "Academy",
                new Cost(Map.of(
                        ResourceType.SILVER,300,
                        ResourceType.WOOD,50,
                        ResourceType.STONE,200,
                        ResourceType.IRON,50
                )),
                50,
                EnumSet.of(BuildingCategory.MILITARY,
                        BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.ACADEMY
        ));
        blueprints.put(BuildingType.BARRACK, new BuildingBlueprint(
                "Barrack",
                new Cost(Map.of(
                        ResourceType.SILVER,50,
                        ResourceType.WOOD,50,
                        ResourceType.STONE,50,
                        ResourceType.IRON,50
                )),
                10,
                EnumSet.of(BuildingCategory.MILITARY),
                Collections.emptySet(),
                BuildingType.BARRACK
        ));
        blueprints.put(BuildingType.SHOOTINGRANGE, new BuildingBlueprint(
                "Shooting Range",
                new Cost(Map.of(
                        ResourceType.SILVER,100,
                        ResourceType.WOOD,75,
                        ResourceType.STONE,25,
                        ResourceType.IRON,10
                )),
                10,
                EnumSet.of(BuildingCategory.MILITARY),
                Collections.emptySet(),
                BuildingType.SHOOTINGRANGE
        ));
        blueprints.put(BuildingType.STABLE, new BuildingBlueprint(
                "Stables",
                new Cost(Map.of(
                        ResourceType.SILVER,300,
                        ResourceType.WOOD,100,
                        ResourceType.GOLD,200,
                        ResourceType.IRON,100
                )),
                30,
                EnumSet.of(BuildingCategory.MILITARY,
                        BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.STABLE
        ));
        blueprints.put(BuildingType.ARENA, new BuildingBlueprint(
                "Arena",
                new Cost(Map.of(
                        ResourceType.GOLD,200,
                        ResourceType.WOOD,200,
                        ResourceType.STONE,200
                )),
                30,
                EnumSet.of(BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.ARENA
        ));
        blueprints.put(BuildingType.BATH, new BuildingBlueprint(
                "Bath",
                new Cost(Map.of(
                        ResourceType.GOLD,200,
                        ResourceType.WOOD,50,
                        ResourceType.STONE,250,
                        ResourceType.IRON,50,
                        ResourceType.SILVER,100
                )),
                50,
                EnumSet.of(BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.BATH
        ));
        blueprints.put(BuildingType.COLLOSEUM, new BuildingBlueprint(
                "Colloseum",
                new Cost(Map.of(
                        ResourceType.GOLD,300,
                        ResourceType.WOOD,250,
                        ResourceType.STONE,400,
                        ResourceType.IRON,100,
                        ResourceType.WATER,250,
                        ResourceType.BEER,200,
                        ResourceType.SILVER,250
                )),
                100,
                EnumSet.of(BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.COLLOSEUM
        ));
        blueprints.put(BuildingType.GARDEN, new BuildingBlueprint(
                "Garden",
                new Cost(Map.of(
                        ResourceType.GOLD,100,
                        ResourceType.WOOD,200,
                        ResourceType.WATER,150
                )),
                15,
                EnumSet.of(BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.GARDEN
        ));
        blueprints.put(BuildingType.LIBRARY, new BuildingBlueprint(
                "Library",
                new Cost(Map.of(
                        ResourceType.STONE,100,
                        ResourceType.WOOD,200,
                        ResourceType.SILVER,50

                )),
                20,
                EnumSet.of(BuildingCategory.TECHNOLOGY,
                        BuildingCategory.ROYAL),
                Collections.emptySet(),
                BuildingType.LIBRARY
        ));
        blueprints.put(BuildingType.UNIVERSITY, new BuildingBlueprint(
                "University",
                new Cost(Map.of(
                        ResourceType.SILVER,100,
                        ResourceType.WOOD,200,
                        ResourceType.STONE,200
                )),
                40,
                EnumSet.of(BuildingCategory.TECHNOLOGY),
                Collections.emptySet(),
                BuildingType.UNIVERSITY
        ));
    }
    public static BuildingBlueprint getBlueprint(BuildingType type) {
        BuildingBlueprint blueprint = blueprints.get(type);
        if (blueprint == null) {
            throw new IllegalArgumentException("Missing definition for this type of building: " + type);
        }
        return blueprint;
    }
}
