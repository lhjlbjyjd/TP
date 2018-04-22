package modifiers;

import Units.Unit;
import Units.UnitInterface;

public class MedievalModifier extends BaseModifier implements UnitInterface {
    MedievalModifier(UnitInterface unit) {
        super(unit);
    }

    @Override
    public void attack(Unit target) {
        wrappedUnit.attack(target);
    }

    @Override
    public void heal(float heal) {
        wrappedUnit.heal(heal);
    }

    @Override
    public void applyDamage(float damage) {
        wrappedUnit.applyDamage(damage * 1.5f);
    }

    @Override
    public void moveTo(int x, int y, int z) {
        wrappedUnit.moveTo(x, y, z);
    }

    @Override
    public void die() {
        wrappedUnit.die();
    }
}
