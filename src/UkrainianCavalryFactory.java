public class UkrainianCavalryFactory extends Factory {
    @Override
    Unit createUnit() {
        return new CavalryUnit(80, 80, 10, 30);
    }
}
