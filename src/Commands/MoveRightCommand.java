package Commands;

import Units.UnitInterface;

public class MoveRightCommand extends Command {

    public MoveRightCommand(UnitInterface unit){
        super(unit);
    }

    @Override
    public void execute() {
        unit.moveTo(1, 0);
    }
}
