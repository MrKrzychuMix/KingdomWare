package Kingdom.Building.Defensive;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class Wall extends Building {
    public Wall() {
        super(BuildingType.WALL);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.MANPOWER,20);
    }
}
