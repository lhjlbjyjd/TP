package Factories;

import Units.*;

public class SwedishCavalryFactory extends Factory {
    @Override
    public Unit createUnit() {
        return new CavalryUnit(100, 100, 20, 20);
    }
}
