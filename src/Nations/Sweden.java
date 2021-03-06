package Nations;

import Factories.SwedishArcherFactory;
import Factories.SwedishCavalryFactory;
import Factories.SwedishMeleeFactory;

public class Sweden extends Nation {
    public Sweden(){
        this.meleeFactory = new SwedishMeleeFactory();
        this.archerFactory = new SwedishArcherFactory();
        this.cavalryFactory = new SwedishCavalryFactory();
    }
}
