package Kingdom.Building.Economy;

import Kingdom.Building.*;
import Resource.ResourceType;
import Kingdom.Treasury.*;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mine extends Building {
private final Set<MiningCategory> actualMiningCategories;
    public Mine() {
        super(BuildingType.MINE);
        this.actualMiningCategories = generateRandomMinigCategories();
    }
    @Override
    public Set<MiningCategory> getMiningCategories() {
        // Zwraca nasz unikalny, wylosowany zbiór kategorii, a nie ten z blueprintu.
        return this.actualMiningCategories;
    }
    @Override
    public void produceResources(Treasury treasury) {

        if (this.hasCategory(MiningCategory.STONE)) {
            treasury.addResource(ResourceType.STONE, 10);
        }
        if (this.hasCategory(MiningCategory.GOLD)) {
            treasury.addResource(ResourceType.GOLD, 2);
        }
        if (this.hasCategory(MiningCategory.IRON)) {
            treasury.addResource(ResourceType.IRON, 5);
        }
        if (this.hasCategory(MiningCategory.SILVER)) {
            treasury.addResource(ResourceType.SILVER, 3);
        }
    }
    private static Set<MiningCategory> generateRandomMinigCategories() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<MiningCategory> categories = EnumSet.of(MiningCategory.STONE);
        // 25%
        if(random.nextInt(100) < 25) {
            categories.add(MiningCategory.GOLD);
        }
        // 40%
        if(random.nextInt(100) < 40) {
            categories.add(MiningCategory.IRON);
        }
        // 25%
        if(random.nextInt(100) < 25) {
            categories.add(MiningCategory.SILVER);
        }
        return categories;
    }

}
