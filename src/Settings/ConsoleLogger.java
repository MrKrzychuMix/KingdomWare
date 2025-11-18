package Settings;

import Interface.IEventListener;
import Interface.IGameEvent;

public class ConsoleLogger implements IEventListener {
    @Override
    public void onEvent(IGameEvent event) {
        System.out.println("[LOG]: "+event.getMessage());
    }

}
