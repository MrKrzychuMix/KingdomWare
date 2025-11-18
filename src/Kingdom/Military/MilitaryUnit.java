package Kingdom.Military;

import Interface.IAttackable;
import Kingdom.Military.Unit.UnitBlueprint;
import Kingdom.Military.Unit.UnitDataRegistry;
import Kingdom.Military.Unit.UnitType;
import Kingdom.Treasury.Cost;

public abstract class MilitaryUnit implements IAttackable {

    private double health;
    private UnitBlueprint blueprint;
    private boolean isAlive;

    public MilitaryUnit(UnitType type){
        this.blueprint = UnitDataRegistry.getBlueprint(type);
        this.health = this.blueprint.maxHealth();
        this.isAlive = true;
    }

    public void takeDamage(double damage) {
        if (!isAlive) {
            System.out.println("This unit is already dead!");
            return;
        }
        double defense = this.getDefensePower();
        double finalDamage = Math.max(0, damage - defense);
        this.health -= finalDamage;
        System.out.println(this.getName() + " received " + finalDamage + " damage! (" + damage + " - " + defense + ")");

        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
            System.out.println(this.getName() + " has died!");
        }
    }

    public double calculateDamageOutput() {
        double ap = this.blueprint.attackPower();
        double rp = this.blueprint.rangedPower();
        double abp = this.blueprint.abilityPower();
        double damageOutput = (ap * rp) + (abp * rp);
        return damageOutput;
    }
    public double getAttackPower() { return blueprint.attackPower(); }
    public double getDefensePower() { return blueprint.defensePower(); }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public String getName(){
        return this.blueprint.name();
    }
    public Cost getMaintenanceCost(){
        return this.blueprint.maintenanceCost();
    }
}
