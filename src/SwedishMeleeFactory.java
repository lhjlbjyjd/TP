public class SwedishMeleeFactory extends Factory{
    @Override
    Unit createUnit() {
        return new MeleeUnit(150, 150, 30, 10);
    }
}
