package Modifiers;

import Commands.Command;
import Main.Player;
import Units.UnitInterface;

public class MedievalModifier extends BaseModifier implements UnitInterface {
    public MedievalModifier(UnitInterface unit) {
        super(unit);
    }

    @Override
    public void attack(UnitInterface target) {
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
    public void moveTo(int x, int y) {
        wrappedUnit.moveTo(x, y);
    }

    @Override
    public void executeCommand(Command command) {
        wrappedUnit.executeCommand(command);
    }

    @Override
    public int getUnitsCount() {
        return wrappedUnit.getUnitsCount();
    }

    @Override
    public boolean cleanUp() {
        return wrappedUnit.cleanUp();
    }

    @Override
    public int getX() {
        return wrappedUnit.getX();
    }

    @Override
    public int getY() {
        return wrappedUnit.getY();
    }

    @Override
    public void setX(int x) {
        wrappedUnit.setX(x);
    }

    @Override
    public void setY(int y) {
        wrappedUnit.setY(y);
    }

    @Override
    public boolean addUnit(Player p, UnitInterface unit) {
        return wrappedUnit.addUnit(p, unit);
    }

    @Override
    public void die() {
        wrappedUnit.die();
    }
}
