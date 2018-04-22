package Factories;

import Units.*;
public class UkrainianArcherFactory extends Factory {
    @Override
    public Unit createUnit() {
        return new ArcherUnit(100, 100, 5, 35);
    }
}
