import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private final Map<EventType, List<EventHandler>> listeners = new HashMap<>();

    public EventBus() {
        for (EventType type : EventType.values()) {
            listeners.put(type, new ArrayList<>());
        }
    }

    public void subscribe(EventType type, EventHandler handler) {
        listeners.get(type).add(handler);
    }

    public void publish(EventType type, String data) {
        System.out.printf("[BUS] Publicando %s%n", type);
        for (EventHandler handler : listeners.get(type)) {
            handler.handle(data);
        }
    }
}
