package Units;

import Commands.Command;
import Main.Player;

import java.util.List;

public class Squad implements UnitInterface {
    private List<UnitInterface> units;

    public Squad(List<UnitInterface> units){
        this.units = units;
    }

    @Override
    public void attack(UnitInterface target) {
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
            u.applyDamage(damage/getUnitsCount());
        }
    }

    @Override
    public void moveTo(int x, int y) {
        for(UnitInterface u : units){
            u.moveTo(x, y);
        }
    }

    @Override
    public void executeCommand(Command command) {
        command.execute();
    }

    @Override
    public int getUnitsCount() {
        int count = 0;
        for(UnitInterface u : units){
            count+= u.getUnitsCount();
        }
        return count;
    }

    @Override
    public boolean cleanUp() {
        for(UnitInterface u : units){
            if(u.cleanUp()){
                units.remove(u);
            }
        }
        return false;
    }

    @Override
    public int getX() {
        return units.get(0).getX();
    }

    @Override
    public int getY() {
        return units.get(0).getY();
    }

    @Override
    public void setX(int x) {
        for(UnitInterface unit : units){
            unit.setX(x);
        }
    }

    @Override
    public void setY(int y) {
        for(UnitInterface unit : units){
            unit.setY(y);
        }
    }

    @Override
    public boolean addUnit(Player p, UnitInterface unit) {
        if(units.size() < 5) {
            units.add(unit);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void die() {
        for(UnitInterface u : units){
            u.die();
        }
    }
}

