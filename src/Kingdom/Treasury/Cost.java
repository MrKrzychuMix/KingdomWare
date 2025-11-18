package Kingdom.Treasury;

import Resource.ResourceType;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public record Cost(Map<ResourceType,Integer> requiredResources) {

    public Cost (Map<ResourceType,Integer> requiredResources) {
        this.requiredResources = Collections.unmodifiableMap(requiredResources);
    }

    public static Cost of(ResourceType type, Integer amount) {
        return new Cost(Map.of(type, amount));
    }

    public static Cost of(ResourceType type1, Integer amount1, ResourceType type2, Integer amount2) {
        return new Cost(Map.of(type1, amount1, type2, amount2));
    }
    public Cost withIncreasedCost(double percentage) { // TO INCREASE THE PRICE
        if (percentage < 0) {
            throw new IllegalArgumentException("Percent cannot be negative number.");
        }
        double factor = 1.0 + (percentage / 100.0);
        return scaleCost(factor);
    }
    public Cost withDiscount(double percentage) { // TO LOWER THE PRICE
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percent has to be in range: [0, 100].");
        }
        double factor = 1.0 - (percentage / 100.0);
        return scaleCost(factor);
    }
    private Cost scaleCost(double factor) { // ASSISTS THE PRICING METHODS
        Map<ResourceType, Integer> scaledResources = this.requiredResources.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) Math.ceil(entry.getValue() * factor)
                ));
        return new Cost(scaledResources);
    }
}
