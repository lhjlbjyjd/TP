package Commands;

import Units.UnitInterface;

public class MoveLeftCommand extends Command {

    public MoveLeftCommand(UnitInterface unit){
        super(unit);
    }

    @Override
    public void execute() {
        unit.moveTo(-1, 0);
    }
}
