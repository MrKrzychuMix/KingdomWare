package Kingdom.Building.Royal;
import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Garden extends Building{
    public Garden() {
        super( BuildingType.GARDEN);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.GOLD,50);
    }
}
