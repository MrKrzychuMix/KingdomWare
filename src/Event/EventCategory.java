package Event;

import java.util.concurrent.ThreadLocalRandom;

public enum EventCategory {
    COST_MODIFIER(5),
    RESOURCE_EVENT(5),
    NEGATIVE_RESOURCE_EVENT(5),
    UNIT_DISCOUNT(5),
    NO_EVENT(80);

    private final int weight;
    EventCategory(int weight){
        this.weight = weight;
    }
    public int getWeight() {
        return this.weight;
    }
    public static EventCategory getRandomCategory() {
        int totalWeight = 0;
        for (EventCategory category : EventCategory.values()) {
            totalWeight += category.getWeight();
        }
        int randomValue = ThreadLocalRandom.current().nextInt(totalWeight);
        for (EventCategory category : EventCategory.values()) {
            if (randomValue < category.getWeight()) {
                return category;
            }
            randomValue -= category.getWeight();
        }
        return NO_EVENT;
    }
}
