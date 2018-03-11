public class UkrainianMeleeFactory extends Factory {
    @Override
    Unit createUnit() {
        return new MeleeUnit(150, 150, 30, 20);
    }
}
