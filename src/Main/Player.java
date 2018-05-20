package Main;

import Nations.Nation;
import Units.Unit;
import Units.UnitInterface;

import java.util.ArrayList;
import java.util.List;

public class Player {

    Nation nation;
    public List<UnitInterface> units = new ArrayList<>();

    Player(Nation nation){
        this.nation = nation;
    }

    void createMelee(){
        units.add(nation.meleeFactory.createUnit());
    }

    void createArcher(){
        units.add(nation.archerFactory.createUnit());
    }

    void createCavalry(){
        units.add(nation.cavalryFactory.createUnit());
    }
}
