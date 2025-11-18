package Kingdom.Military.Unit;

import Kingdom.Military.MilitaryUnit;
import Kingdom.Military.Unit.Type.*;

public class UnitFactory {
    public static MilitaryUnit createUnit(UnitType type){
        switch(type) {
            case SWORDSMAN:
                return new Swordsman();
            case ARCHER:
                return new Archer();
            case CROSSBOWMAN:
                return new Crossbowman();
            case KNIGHT:
                return new Knight();
            case MAGE:
                return new Mage();
            default:
                throw new IllegalArgumentException("Unknown unit type: " + type);
        }
    }
}
