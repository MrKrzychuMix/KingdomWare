package Kingdom.Military.Unit;

import Kingdom.Building.BuildingType;
import Kingdom.Treasury.Cost;

public record UnitBlueprint(
        String name,
        double attackPower,
        double defensePower,
        double abilityPower,
        double rangedPower,
        Cost recruitmentCost,
        Cost maintenanceCost,
        double maxHealth,
        BuildingType requiredBuilding,
        boolean recruitableWithoutBuilding
) {
}
