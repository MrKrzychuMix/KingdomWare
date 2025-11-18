package Event;

import Interface.IGameEvent;
import Kingdom.Military.Unit.UnitType;

public record UnitCostChangeEvent(UnitType type, double percentage, String message) implements IGameEvent {
    @Override
    public String getMessage() {
        return this.message;
    }
}
