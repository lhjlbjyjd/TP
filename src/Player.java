import Units.*;
import Nations.Nation;

import java.util.ArrayList;
import java.util.List;

public class Player {

    Nation nation;
    List<Unit> units = new ArrayList<Unit>();

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
