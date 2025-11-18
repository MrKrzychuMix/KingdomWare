package Event;

import Interface.IGameEvent;
import Resource.ResourceType;

public record ResourceLostEvent(ResourceType resourceType, int amount, String message) implements IGameEvent {
    @Override
    public String getMessage() {
        return this.message;
    }
}
