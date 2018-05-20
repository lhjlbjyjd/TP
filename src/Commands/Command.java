package Commands;

import Units.UnitInterface;

public abstract class Command {
    protected UnitInterface unit;

    public Command(UnitInterface unit){
        this.unit = unit;
    }

    public abstract void execute();
}
