package Resource;

// Wzorzec dla zasobów
public class ResourceStock {
    private final ResourceType type;
    private int quantity;

    public ResourceStock(ResourceType type, int initialQuantity) {
        this.type = type;
        this.quantity = initialQuantity;
    }

    public void add(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add a negative amount.");
        }
        this.quantity += amount;
    }
    public boolean checkEnough(int cost){
        boolean enough;
        if(quantity - cost >= 0) {
            enough = true;
        } else {
            enough = false;
        }
        return enough;
    }
    public void remove(int amount) {
        if(checkEnough(amount)){
            quantity = quantity - amount;
        } else {
            throw new IllegalArgumentException("Cannot remove more than you already have.");
        }
    }

    public ResourceType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}