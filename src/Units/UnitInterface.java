package Units;

public interface UnitInterface {


    void attack(Unit target);

    void heal(float heal);

    void applyDamage(float damage);

    void moveTo(int x, int y, int z);

    void die();
}
