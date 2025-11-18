package Kingdom.Building;

public class ConstructionJob {
    private Building building;
    private int turnsRemaining;
    public ConstructionJob(Building buildingToConstruct){
        if (buildingToConstruct == null) {
            throw new IllegalArgumentException("Kingdom.Building cannot be null.");
        }
        if (buildingToConstruct.getConstructionTime() <= 0) {
            throw new IllegalArgumentException("Construction time cannot equal or be lower than 0.");
        }
        this.building = buildingToConstruct;
        this.turnsRemaining = buildingToConstruct.getConstructionTime();
    }
    public Building getBuilding() {
        return this.building;
    }

    public int getTurnsRemaining() {
        return this.turnsRemaining;
    }

    public boolean advanceTurn() {
        this.turnsRemaining--;
        return isFinished();
    }

    public boolean isFinished() {
        return this.turnsRemaining <= 0;
    }
}
