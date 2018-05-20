package Commands;

import Units.UnitInterface;

public class MoveUpCommand extends Command{

    public MoveUpCommand(UnitInterface unit){
        super(unit);
    }

    @Override
    public void execute() {
        unit.moveTo(0, 1);
    }
}
