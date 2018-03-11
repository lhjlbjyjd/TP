public class Unit {
    private float health, maxHealth, armor, damage;

    Unit(float health, float maxHealth, float armor, float damage){
        this.health = health;
        this.maxHealth = maxHealth;
        this.armor = armor;
        this.damage = damage;
    }

    public void attack(Unit target){
        target.applyDamage(damage);
    }

    public void heal(float heal){
        health = (health + heal) < maxHealth ? health + heal : maxHealth;
    }

    public void applyDamage(float damage){
        health -= damage*armor;
        if(health <= 0){
            die();
        }
    }

    public void die(){
        health = 0;
    }
}
