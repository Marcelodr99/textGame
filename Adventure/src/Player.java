
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private String name;
    private int health;
    private int minDamage;
    private int maxDamage;
    private int numHealthPotions;
    private int healthPotionHealAmount;
    // ... other player variables and methods

    public Player(String name, int health, int minDamage, int maxDamage, int numHealthPotions, int healthPotionHealAmount) {
        this.name = name;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.numHealthPotions = numHealthPotions;
        this.healthPotionHealAmount = healthPotionHealAmount;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);
    }

    // ... other getter methods for player variables

    public void attack(Enemy enemy) {
        int damage = getAttackDamage();
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + damage + " damage!");
    }

    public void drinkHealthPotion() {
        if (numHealthPotions > 0) {
            int healAmount = Math.min(healthPotionHealAmount, 100 - health);
            health += healAmount;
            numHealthPotions--;
            System.out.println(name + " drinks a health potion, healing for " + healAmount + " HP.");
            System.out.println(name + " now has " + health + " HP.");
            System.out.println(name + " has " + numHealthPotions + " health potion(s) left.");
        } else {
            System.out.println(name + " has no health potions left. Defeat enemies for a chance to get one!");
        }
    }

    public void acquireHealthPotion() {
        numHealthPotions++;
        System.out.println(name + " acquires a health potion!");
        System.out.println(name + " now has " + numHealthPotions + " health potion(s).");
    }
    public boolean isAlive() {
        return health > 0;
    }
}
