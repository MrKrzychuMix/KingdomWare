package Kingdom.Building.Economy;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class LumberMill extends Building {
    public LumberMill() {
        super(BuildingType.LUMBERMILL);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.WOOD,15);
    }
}
