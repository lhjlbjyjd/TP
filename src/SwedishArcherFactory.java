public class SwedishArcherFactory extends Factory {
    @Override
    Unit createUnit() {
        return new ArcherUnit(100, 100, 10, 30);
    }
}
