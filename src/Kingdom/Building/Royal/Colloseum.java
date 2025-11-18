package Kingdom.Building.Royal;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Colloseum extends Building {
    public Colloseum() {
        super(BuildingType.COLLOSEUM);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.GOLD,250);
    }
}
