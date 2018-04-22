package modifiers;

import Units.Unit;
import Units.UnitInterface;

public abstract class BaseModifier {

    UnitInterface wrappedUnit;

    BaseModifier(UnitInterface unit){
        wrappedUnit = unit;
    };
}
