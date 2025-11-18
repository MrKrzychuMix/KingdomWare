package Kingdom.Military.Unit.Type;

import Interface.IAttackable;
import Interface.IRecruitable;
import Kingdom.Military.MilitaryUnit;
import Kingdom.Military.Unit.UnitType;
import Kingdom.Treasury.Cost;

public class Swordsman extends MilitaryUnit implements IRecruitable, IAttackable {


    public Swordsman() {
        super(UnitType.SWORDSMAN);
    }

    @Override
    public void recruit() {

    }

    @Override
    public Cost getRecruitmentCost() {
        return null;
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
