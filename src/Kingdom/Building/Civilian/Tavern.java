package Kingdom.Building.Civilian;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Tavern extends Building {

    public Tavern() {
        super(BuildingType.TAVERN);

    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.removeResource(Cost.of(ResourceType.GRAIN,10,ResourceType.WATER,10));
        treasury.addResource(ResourceType.BEER,10);
    }
}
