import java.util.concurrent.ThreadLocalRandom;
public class Enemy {
    private String name;
    private int health;
    private int minDamage;
    private int maxDamage;

    public Enemy(String name, int health, int minDamage, int maxDamage) {
        this.name = name;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + damage + " damage!");
    }

    public void attack(Player player) {
        int damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);
        player.takeDamage(damage);
        System.out.println(name + " attacks " + player.getName() + " for " + damage + " damage!");
    }
    public boolean isAlive() {
        return health > 0;
    }
}
