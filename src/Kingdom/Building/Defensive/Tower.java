package Kingdom.Building.Defensive;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class Tower extends Building {

    public Tower() {
        super(BuildingType.TOWER);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.MANPOWER,10);
    }
}
