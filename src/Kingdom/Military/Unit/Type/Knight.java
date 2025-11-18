package Kingdom.Military.Unit.Type;

import Interface.IAttackable;
import Kingdom.Military.MilitaryUnit;
import Kingdom.Military.Unit.UnitType;

public class Knight extends MilitaryUnit implements IAttackable {


    public Knight() {
        super(UnitType.KNIGHT);
    }


    @Override
    public void takeDamage() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getMaxHealth() {
        return 0;
    }
}
