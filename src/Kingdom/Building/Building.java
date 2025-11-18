package Kingdom.Building;

import Kingdom.Military.Unit.UnitBlueprint;
import Kingdom.Military.Unit.UnitDataRegistry;
import Kingdom.Treasury.Cost;
import Kingdom.Treasury.Treasury;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public abstract class Building {
    private BuildingType type;
    private BuildingBlueprint blueprint;

    public Building(BuildingType type) {
        this.type = type;
        this.blueprint = BuildingDataRegistry.getBlueprint(type);
    }

    public String getName() {
        return blueprint.name();
    }

    public Cost getConstructionCost() {
        return blueprint.constructionCost();
    }

    public int getConstructionTime() {
        return blueprint.constructionTime();
    }
    public boolean hasCategory(BuildingCategory category) {
        return blueprint.buildingCategories().contains(category);
    }
    public boolean hasCategory(MiningCategory category) {
        return blueprint.miningCategories().contains(category);
    }

    public Set<BuildingCategory> getBuildingCategories() {
        return blueprint.buildingCategories();
    }
    public Set<MiningCategory> getMiningCategories() {
        return blueprint.miningCategories();
    }

    public BuildingType getType() {
        return blueprint.type();
    }

    public abstract void produceResources(Treasury treasury);
}

