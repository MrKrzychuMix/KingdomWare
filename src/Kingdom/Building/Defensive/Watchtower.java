package Kingdom.Building.Defensive;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class Watchtower extends Building {
    public Watchtower() {
        super(BuildingType.WATCHTOWER);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.MANPOWER,5);
    }
}
