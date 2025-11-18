package Kingdom.Treasury;

import Event.RandomResourceEvent;
import Event.ResourceLostEvent;
import Interface.IEventListener;
import Interface.IGameEvent;
import Resource.ResourceStock;
import Resource.ResourceType;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Treasury implements IEventListener {
    private final Map<ResourceType, ResourceStock> resources;

    public Treasury() {

        this.resources = new EnumMap<>(ResourceType.class);
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, new ResourceStock(type, 100));
        }
    }
    public void addResource(ResourceType type, int amount){
        ResourceStock stock = resources.get(type);
        if (stock != null) {
            stock.add(amount);
        }
    }
    public void removeResource(ResourceType type, int amount) {
        ResourceStock stock = resources.get(type);
        if (stock != null) {
            stock.remove(amount);
        } else if (amount > 0) {
            throw new IllegalStateException("Insufficient resources of: " + type);
        }
    }
    public void removeResource(Cost cost) {
        if (!hasEnough(cost)) {
            throw new IllegalStateException("Insufficient resources, to cover the costs.");
        }
        for (Map.Entry<ResourceType, Integer> entry : cost.requiredResources().entrySet()) {
            this.removeResource(entry.getKey(), entry.getValue());
        }
    }

    public int getResourceAmount(ResourceType type) {
        return resources.get(type).getQuantity();
    }

    public boolean hasEnough(ResourceType type, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative number.");
        }
        return getResourceAmount(type) >= amount;
    }
    public boolean hasEnough(Cost cost) {
        for (Map.Entry<ResourceType, Integer> entry : cost.requiredResources().entrySet()) {
            if (!this.hasEnough(entry.getKey(), entry.getValue())) {

            return false;
        }
    }
    return true;
    }

    @Override
    public void onEvent(IGameEvent event) {

        if (event instanceof RandomResourceEvent foundEvent) {
            ResourceType type = foundEvent.resourceType();
            int amount = foundEvent.amount();
            this.addResource(type, amount);
            System.out.println("DEBUG (Treasury): Event occured, added " + amount + " " + type);
        }

         if (event instanceof ResourceLostEvent lostEvent) {
             this.removeResource(lostEvent.resourceType(), lostEvent.amount());
         }
    }
    public String getAllResourcesAsString() {
        return this.resources.entrySet().stream()
                .map(entry -> {
                    ResourceType type = entry.getKey();
                    ResourceStock stock = entry.getValue();
                    return type.name() + ": " + stock.getQuantity();
                })
                .collect(Collectors.joining(", "));
    }
}



