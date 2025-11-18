package Kingdom.Building.Economy;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class Farm extends Building {

    public Farm() {
        super(BuildingType.FARM);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.FOOD,5);
        treasury.addResource(ResourceType.WATER,5);
        treasury.addResource(ResourceType.GRAIN,5);
    }
}
