package Kingdom.Building.Civilian.Trade;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.List;

public class Shop extends Building {
    private final List<TradeOffer> availableOffers;

    public Shop() {
        super(BuildingType.SHOP);

        this.availableOffers = List.of(
                //                   SELL                    FOR                     BUY
                // GOLD
                        new TradeOffer(ResourceType.GOLD,1,ResourceType.SILVER,1),
                        new TradeOffer(ResourceType.GOLD,1,ResourceType.IRON,2),
                        new TradeOffer(ResourceType.GOLD,5,ResourceType.FOOD,15),
                        new TradeOffer(ResourceType.GOLD,5,ResourceType.WOOD,20),
                        new TradeOffer(ResourceType.GOLD,5,ResourceType.STONE,20),
                        new TradeOffer(ResourceType.GOLD,5,ResourceType.WATER,15),
                        new TradeOffer(ResourceType.GOLD,5,ResourceType.BEER,10),
                // SILVER
                        new TradeOffer(ResourceType.SILVER,1,ResourceType.GOLD,1),
                        new TradeOffer(ResourceType.SILVER,1,ResourceType.IRON,2),
                        new TradeOffer(ResourceType.SILVER,5,ResourceType.FOOD,15),
                        new TradeOffer(ResourceType.SILVER,5,ResourceType.WOOD,20),
                        new TradeOffer(ResourceType.SILVER,5,ResourceType.STONE,20),
                        new TradeOffer(ResourceType.SILVER,5,ResourceType.WATER,15),
                        new TradeOffer(ResourceType.SILVER,5,ResourceType.BEER,10),
                // IRON
                        new TradeOffer(ResourceType.IRON,1,ResourceType.SILVER,1),
                        new TradeOffer(ResourceType.IRON,2,ResourceType.GOLD,1),
                        new TradeOffer(ResourceType.IRON,3,ResourceType.FOOD,15),
                        new TradeOffer(ResourceType.IRON,5,ResourceType.WOOD,20),
                        new TradeOffer(ResourceType.IRON,5,ResourceType.STONE,20),
                        new TradeOffer(ResourceType.IRON,3,ResourceType.WATER,15),
                        new TradeOffer(ResourceType.IRON,5,ResourceType.BEER,10),
                // BEER
                        new TradeOffer(ResourceType.BEER,1,ResourceType.SILVER,1),
                        new TradeOffer(ResourceType.BEER,1,ResourceType.IRON,2),
                        new TradeOffer(ResourceType.BEER,2,ResourceType.FOOD,15),
                        new TradeOffer(ResourceType.BEER,3,ResourceType.WOOD,20),
                        new TradeOffer(ResourceType.BEER,3,ResourceType.STONE,20),
                        new TradeOffer(ResourceType.BEER,2,ResourceType.WATER,15),
                        new TradeOffer(ResourceType.BEER,1,ResourceType.GOLD,1)
                );
    }

    @Override
    public void produceResources(Treasury treasury) {
        System.out.println("Shop does not produce any local goods, come visit our trading post to check our functionality");
    }
    public List<TradeOffer> getAvailableOffers() {
        return this.availableOffers;
    }
    public boolean executeTrade(TradeOffer offer,Treasury playerTreasury){
        Cost playerCost = Cost.of(offer.resourceToSell(),offer.amountToSell());
        if (playerTreasury.hasEnough(playerCost)){
            playerTreasury.removeResource(playerCost);
            playerTreasury.addResource(offer.resourceToBuy(),offer.amountToBuy());
            System.out.println("Trade sucessful");
            return true;
        } else {
            System.out.println("Trade failed, not enough resources in treasury");
            return false;
        }
    }
}
