package Kingdom.Building.Technology;


import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class Library extends Building{
    public Library() {
        super(BuildingType.LIBRARY);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.TECHNOLOGY,15);
    }
}
