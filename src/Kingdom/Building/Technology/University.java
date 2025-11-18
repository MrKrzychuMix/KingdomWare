package Kingdom.Building.Technology;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;

public class University extends Building {
    public University() {
        super( BuildingType.UNIVERSITY);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.addResource(ResourceType.TECHNOLOGY,35);
    }
}
