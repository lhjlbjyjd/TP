package Units;

import Commands.Command;
import Main.Player;

import java.util.ArrayList;
import java.util.List;

public class Unit implements UnitInterface {
    private float health, maxHealth, armor, damage;
    private int x = -1, y = -1;

    Unit(float health, float maxHealth, float armor, float damage){
        this.health = health;
        this.maxHealth = maxHealth;
        this.armor = armor;
        this.damage = damage;
    }

    @Override
    public void attack(UnitInterface target){
        System.out.println("Attacking unit");
        target.applyDamage(damage);
    }

    @Override
    public void heal(float heal){
        health = (health + heal) < maxHealth ? health + heal : maxHealth;
    }

    @Override
    public void applyDamage(float damage){
        health -= damage;
        System.out.printf("%f damage done. Current hp: %f\n", damage, health);
    }

    @Override
    public void moveTo(int deltaX, int deltaY){
        x+= deltaX;
        y+= deltaY;
    }

    @Override
    public void executeCommand(Command command) {
        command.execute();
    }

    @Override
    public int getUnitsCount() {
        return 1;
    }

    @Override
    public boolean cleanUp() {
        if(health <= 0){
            die();
            return true;
        }
        return false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean addUnit(Player p, UnitInterface unit) {
        List<UnitInterface> list = new ArrayList<>();
        list.add(this);
        list.add(unit);
        p.units.add(new Squad(list));
        p.units.remove(this);
        p.units.remove(unit);
        return true;
    }

    @Override
    public void die(){}
}
