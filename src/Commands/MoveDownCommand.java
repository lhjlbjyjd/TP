package Commands;

import Units.UnitInterface;

public class MoveDownCommand extends Command {

    public MoveDownCommand(UnitInterface unit){
        super(unit);
    }

    @Override
    public void execute() {
        unit.moveTo(0, -1);
    }
}
