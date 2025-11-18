package Kingdom.Building;

import Kingdom.Building.Civilian.House;
import Kingdom.Building.Civilian.Tavern;
import Kingdom.Building.Civilian.Trade.Shop;
import Kingdom.Building.Defensive.Castle;
import Kingdom.Building.Defensive.Tower;
import Kingdom.Building.Defensive.Wall;
import Kingdom.Building.Defensive.Watchtower;
import Kingdom.Building.Economy.Farm;
import Kingdom.Building.Economy.LumberMill;
import Kingdom.Building.Economy.Mine;
import Kingdom.Building.Economy.Windmill;
import Kingdom.Building.Military.Academy;
import Kingdom.Building.Military.Barrack;
import Kingdom.Building.Military.ShootingRange;
import Kingdom.Building.Military.Stable;
import Kingdom.Building.Royal.Arena;
import Kingdom.Building.Royal.Bath;
import Kingdom.Building.Royal.Colloseum;
import Kingdom.Building.Royal.Garden;
import Kingdom.Building.Technology.Library;
import Kingdom.Building.Technology.University;

public class BuildingFactory {
    public static Building createBuilding(BuildingType type){
        switch (type) {
            case SHOP:
                return new Shop();
            case HOUSE:
                return new House();
            case TAVERN:
                return new Tavern();
            case CASTLE:
                return new Castle();
            case TOWER:
                return new Tower();
            case WALL:
                return new Wall();
            case WATCHTOWER:
                return new Watchtower();
            case FARM:
                return new Farm();
            case LUMBERMILL:
                return new LumberMill();
            case MINE:
                return new Mine();
            case WINDMILL:
                return new Windmill();
            case ACADEMY:
                return new Academy();
            case BARRACK:
                return new Barrack();
            case SHOOTINGRANGE:
                return new ShootingRange();
            case STABLE:
                return new Stable();
            case ARENA:
                return new Arena();
            case BATH:
                return new Bath();
            case COLLOSEUM:
                return new Colloseum();
            case GARDEN:
                return new Garden();
            case LIBRARY:
                return new Library();
            case UNIVERSITY:
                return new University();
            default:

                throw new IllegalArgumentException("Workers didn't know the recipe for this type of building: " + type);
        }
    }
}
