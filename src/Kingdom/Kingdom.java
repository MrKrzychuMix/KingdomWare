package Kingdom;

import Kingdom.Building.*;
import Kingdom.Military.Army;
import Kingdom.Treasury.Treasury;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kingdom {

    private final String name;
    private Treasury treasury;
    private Army army;
    private List<Building> completedBuildings;
    private List<ConstructionJob> ongoingConstructions ;


    public Kingdom(String name){
        this.name = name;
        this.treasury = new Treasury();
        this.army = new Army();
        this.completedBuildings = new ArrayList<>();
        this.ongoingConstructions= new ArrayList<>();
        System.out.println("New kingdom of "+name+" has risen!");
    }

    public void startConstruction(Building building){
        ConstructionJob newJob = new ConstructionJob(building);
        this.ongoingConstructions.add(newJob);
        System.out.println("Process of building "+building.getName()+" will last for "+ building.getConstructionTime()+" turns.");
    }

    public void processConstructionQueue() {
        List<ConstructionJob> finishedJobs = new ArrayList<>();
        for(ConstructionJob job : this.ongoingConstructions){
            if(job.advanceTurn()){
                finishedJobs.add(job);
            }
        }
        for(ConstructionJob finishedJob : finishedJobs){
            Building buildingToAdd = finishedJob.getBuilding();
            this.completedBuildings.add(buildingToAdd);
            System.out.println("Construction finished for: "+ buildingToAdd.getName());
        }
        if (!finishedJobs.isEmpty()){
            this.ongoingConstructions.removeAll(finishedJobs);
        }
    }

    public List<Building> getCompletedBuildings(){
        return Collections.unmodifiableList(completedBuildings);
    }

    public Treasury getTreasury() {
        return treasury;
    }

    public Army getArmy() {
        return army;
    }

    public List<ConstructionJob> getOngoingConstructions() {
        return ongoingConstructions;
    }
}
