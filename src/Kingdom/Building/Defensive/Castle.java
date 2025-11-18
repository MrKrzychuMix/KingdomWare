package Kingdom.Building.Defensive;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Castle extends Building {
    public Castle() {
        super(BuildingType.CASTLE);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.MANPOWER,200);
    }
}
