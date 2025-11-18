package Settings;

import Interface.IEventListener;
import Interface.IGameEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private Map<Class<?extends IGameEvent>, List<IEventListener>> subscriptions;
    public EventBus(){
        this.subscriptions = new HashMap<>();
    }
    public void subscribe(Class<?extends IGameEvent> eventType, IEventListener listener){
        List<IEventListener> listeners = subscriptions.computeIfAbsent(eventType, k -> new ArrayList<>());
        listeners.add(listener);
        System.out.println("DEBUG: Subscriber registered " + listener.getClass().getSimpleName() + " for event " + eventType.getSimpleName());
    }
    public void publish(IGameEvent event) {
        if (event == null) {
            System.err.println("WARNING: NULL event compromised.");
            return;
        }
        System.out.println("DEBUG: Publishing event: " + event.getClass().getSimpleName());
        Class<?> eventType = event.getClass();

        List<IEventListener> listeners = subscriptions.get(eventType);


        if (listeners != null && !listeners.isEmpty()) {
            System.out.println("DEBUG: Found " + listeners.size() + " subscribers for " + event.getClass().getSimpleName());

            for (IEventListener listener : new ArrayList<>(listeners)) {
                listener.onEvent(event);
            }
        } else {
            System.out.println("DEBUG: No subscribers found for " + event.getClass().getSimpleName());
        }
    }
}
