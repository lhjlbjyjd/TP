package Factories;

import Units.*;

public class SwedishArcherFactory extends Factory {
    @Override
    public Unit createUnit() {
        return new ArcherUnit(100, 100, 10, 30);
    }
}
