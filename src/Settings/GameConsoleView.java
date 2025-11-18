package Settings;

import Interface.IEventListener;
import Interface.IGameEvent;

public class GameConsoleView implements IEventListener {

    @Override
    public void onEvent(IGameEvent event) {
        System.out.println("[EVENT] "+ event.getMessage());
    }
    public void runGameLoop(){

    }
}
