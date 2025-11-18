package Settings;

import Event.CostModifier;
import Event.TurnStartedEvent;
import Event.UnitCostChangeEvent;
import Interface.IEventListener;
import Interface.IGameEvent;
import Kingdom.Military.Unit.UnitType;
import Kingdom.Treasury.Cost;

import java.util.EnumMap;
import java.util.Map;

public class RecruitmentCostCalculator implements IEventListener {
    private double globalCostModifierPercentage = 0.0; // in percentages f.e 10 -> +10%, -20 -> -20%
    private Map<UnitType, Double> unitSpecificModifierPercentages = new EnumMap<>(UnitType.class);

    public Cost calculateRecruitmentCost(UnitType unitType, Cost baseCost) {
        Double specificModifier = unitSpecificModifierPercentages.get(unitType);
        double finalModifierPercentage;
        if (specificModifier != null) {
            finalModifierPercentage = specificModifier;
            System.out.println("DEBUG: Specific modifier applied: " + finalModifierPercentage + "% for " + unitType);
        } else {
            finalModifierPercentage = globalCostModifierPercentage;
            System.out.println("DEBUG: Global modifier applied: " + finalModifierPercentage + "%");
        }
        if (finalModifierPercentage == 0.0) {
            return baseCost;
        }
        if (finalModifierPercentage > 0) {
            return baseCost.withIncreasedCost(finalModifierPercentage);
        } else {
            return baseCost.withDiscount(Math.abs(finalModifierPercentage));
        }
    }

    @Override
    public void onEvent(IGameEvent event) {
        if (event instanceof TurnStartedEvent) {
            this.globalCostModifierPercentage = 0.0;
            this.unitSpecificModifierPercentages.clear();
            System.out.println("DEBUG: Cost modifiers have been reseted.");
        }
        if (event instanceof UnitCostChangeEvent modifierEvent) {
            unitSpecificModifierPercentages.put(modifierEvent.type(), modifierEvent.percentage());
            System.out.println("DEBUG: Registered a discount for " + modifierEvent.type());
        }
        if (event instanceof CostModifier modifierEvent) {
            this.globalCostModifierPercentage = modifierEvent.penaltyPercentage();
            System.out.println("DEBUG: Registered global changes of costs of "+ modifierEvent.penaltyPercentage());
        }

    }


}
