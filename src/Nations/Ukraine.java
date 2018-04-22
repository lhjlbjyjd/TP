package Nations;

import Factories.*;

public class Ukraine extends Nation {
    Ukraine(){
        this.meleeFactory = new UkrainianMeleeFactory();
        this.archerFactory = new UkrainianArcherFactory();
        this.cavalryFactory = new UkrainianCavalryFactory();
    }
}
