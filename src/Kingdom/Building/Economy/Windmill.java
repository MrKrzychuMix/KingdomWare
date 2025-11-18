package Kingdom.Building.Economy;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Windmill extends Building {
    public Windmill() {
        super(BuildingType.WINDMILL);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.removeResource(Cost.of(ResourceType.GRAIN,10));
        treasury.addResource(ResourceType.FOOD,30);
        treasury.addResource(ResourceType.WATER,10);
    }
}
