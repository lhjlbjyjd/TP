package Factories;

import Units.*;

public class UkrainianCavalryFactory extends Factory {
    @Override
    public Unit createUnit() {
        return new CavalryUnit(80, 80, 10, 30);
    }
}
