package Units;

import Commands.Command;
import Main.Player;

public interface UnitInterface {

    void attack(UnitInterface target);

    void heal(float heal);

    void applyDamage(float damage);

    void moveTo(int deltaX, int deltaY);

    void executeCommand(Command command);

    int getUnitsCount();

    boolean cleanUp();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    boolean addUnit(Player p, UnitInterface unit);

    void die();
}