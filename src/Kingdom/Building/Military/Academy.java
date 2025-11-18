package Kingdom.Building.Military;

import Kingdom.Building.*;
import Interface.IRecruitingBuilding;
import Kingdom.Military.Unit.UnitType;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class Academy extends Building implements IRecruitingBuilding {
    private Set<UnitType> recruitableUnits;
    public Academy() {
        super(BuildingType.ACADEMY);
        this.recruitableUnits = EnumSet.of(UnitType.MAGE);
    }

    @Override
    public void produceResources(Treasury treasury) {

    }
    @Override
    public Set<UnitType> getRecruitableUnits() {
        return recruitableUnits;
    }
}
