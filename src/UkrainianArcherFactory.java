public class UkrainianArcherFactory extends Factory {
    @Override
    Unit createUnit() {
        return new ArcherUnit(100, 100, 5, 35);
    }
}
