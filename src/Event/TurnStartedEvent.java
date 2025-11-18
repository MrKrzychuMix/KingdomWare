package Event;

import Interface.IGameEvent;

public record TurnStartedEvent(int turnNumber) implements IGameEvent {

    @Override
    public String getMessage() {
        return "Turn "+ turnNumber+ " has started";
    }

}
