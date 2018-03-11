public class SwedishCavalryFactory extends Factory {
    @Override
    Unit createUnit() {
        return new CavalryUnit(100, 100, 20, 20);
    }
}
