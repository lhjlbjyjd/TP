public class Player {

    Factory meleeFactory;
    Factory archerFactory;
    Factory cavalryFactory;

    Player(Factory m, Factory a, Factory c){
        meleeFactory = m;
        archerFactory = a;
        cavalryFactory = c;
    }

    Unit createMelee(){
        return meleeFactory.createUnit();
    }

    Unit createArcher(){
        return archerFactory.createUnit();
    }

    Unit createCavalry(){
        return cavalryFactory.createUnit();
    }
}
