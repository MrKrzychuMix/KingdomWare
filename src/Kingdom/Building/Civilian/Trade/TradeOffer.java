package Kingdom.Building.Civilian.Trade;

import Resource.ResourceType;

public record TradeOffer (
        ResourceType resourceToSell,
        int amountToSell,
        ResourceType resourceToBuy,
        int amountToBuy
) {
    @Override
    public String toString() {
        return "Trade "+ amountToSell + " "+ resourceToSell+ " for "+amountToBuy+" "+resourceToBuy;
    }
}
