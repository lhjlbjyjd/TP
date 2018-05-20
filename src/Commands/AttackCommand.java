package Commands;

import Units.Unit;
import Units.UnitInterface;

public class AttackCommand extends Command {

    UnitInterface target;

    public AttackCommand(UnitInterface unit, UnitInterface target){
        super(unit);
        this.target = target;
    }

    @Override
    public void execute() {
        unit.attack(target);
    }
}
