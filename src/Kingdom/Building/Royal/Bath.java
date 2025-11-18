package Kingdom.Building.Royal;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Bath extends Building {
    public Bath() {
        super( BuildingType.BATH);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.GOLD,100);
    }
}
