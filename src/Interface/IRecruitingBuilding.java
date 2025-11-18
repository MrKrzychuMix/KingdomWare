package Interface;

import Kingdom.Military.Unit.UnitType;
import java.util.Set;


public interface IRecruitingBuilding {
    Set<UnitType> getRecruitableUnits();
}
