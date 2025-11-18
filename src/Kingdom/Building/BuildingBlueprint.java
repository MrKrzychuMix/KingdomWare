package Kingdom.Building;

import Kingdom.Treasury.Cost;

import java.util.Set;

public record BuildingBlueprint(
        String name,
        Cost constructionCost,
        int constructionTime,
        Set<BuildingCategory>buildingCategories,
        Set<MiningCategory> miningCategories,
        BuildingType type
) {
}
