package Event;

import Interface.IGameEvent;
import Kingdom.Military.Unit.UnitType;

public record CostModifier(double penaltyPercentage, String message) implements IGameEvent {

    @Override
    public String getMessage() {
        return this.message;
    }
}
