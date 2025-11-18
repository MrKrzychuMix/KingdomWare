package Kingdom.Building.Civilian;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;

public class House extends Building {

    public House() {
        super(BuildingType.HOUSE);
    }

    @Override
    public void produceResources(Treasury treasury) {
        treasury.removeResource(Cost.of(ResourceType.FOOD,1,ResourceType.WATER,1));
        treasury.addResource(ResourceType.MANPOWER,5);
    }
}
