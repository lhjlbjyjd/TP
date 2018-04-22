package Factories;

import Units.*;

public class UkrainianMeleeFactory extends Factory {
    @Override
    public Unit createUnit() {
        return new MeleeUnit(150, 150, 30, 20);
    }
}
