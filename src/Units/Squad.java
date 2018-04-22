package Units;

import java.util.List;

public class Squad implements UnitInterface {
    private List<UnitInterface> units;

    public Squad(List<UnitInterface> units){
        this.units = units;
    }

    @Override
    public void attack(Unit target) {
        for(UnitInterface u : units){
            u.attack(target);
        }
    }

    @Override
    public void heal(float heal) {
        for(UnitInterface u : units){
            u.heal(heal);
        }
    }

    @Override
    public void applyDamage(float damage) {
        for(UnitInterface u : units){
            u.applyDamage(damage);
        }
    }

    @Override
    public void moveTo(int x, int y, int z) {
        for(UnitInterface u : units){
            u.moveTo(x, y, z);
        }
    }

    @Override
    public void die() {

    }
}

