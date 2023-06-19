import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Dungeon");
        createPlayer();
        while (true) {
            createEnemy();
            fight();
            if (!continueGame()) {
                break;
            }
        }
        endGame();
    }

    private void createPlayer() {
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName, 100, 25, 50, 3, 30);
    }

    private void createEnemy() {
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        String enemyName = enemies[ThreadLocalRandom.current().nextInt(enemies.length)];
        int enemyHealth = ThreadLocalRandom.current().nextInt(50, 76);  // Random enemy health between 50-75
        int enemyMinDamage = ThreadLocalRandom.current().nextInt(20, 31);  // Random enemy minimum damage between 20-30
        int enemyMaxDamage = ThreadLocalRandom.current().nextInt(31, 41);  // Random enemy maximum damage between 30-40
        enemy = new Enemy(enemyName, enemyHealth, enemyMinDamage, enemyMaxDamage);
        System.out.println("A wild " + enemy.getName() + " appears!");
    }

    private void fight() {
        while (player.isAlive() && enemy.isAlive()) {
            displayPlayerStats();
            displayEnemyStats();
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Attack");
            System.out.println("2. Drink health potion");
            System.out.println("3. Run!");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    player.attack(enemy);
                    if (enemy.isAlive()) {
                        enemy.attack(player);
                    }
                    break;
                case 2:
                    player.drinkHealthPotion();
                    break;
                case 3:
                    System.out.println("You run away from the " + enemy.getName() + "!");
                    return;
                default:
                    System.out.println("Invalid command!");
            }
        }

        if (!player.isAlive()) {
            System.out.println("You have taken too much damage, you are too weak to go on!");
            gameOver();
        } else if (!enemy.isAlive()) {
            System.out.println("Congratulations! You defeated the " + enemy.getName() + "!");
            player.acquireHealthPotion();
        }
    }


    private boolean continueGame() {
        System.out.println("----------------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("1. Continue fighting");
        System.out.println("2. Exit dungeon");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        while (choice != 1 && choice != 2) {
            System.out.println("Invalid command! Please choose again.");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
        }

        if (choice == 1) {
            System.out.println("You continue on your adventure!");
            return true;
        } else {
            System.out.println("You exit the dungeon, successful from your adventures!");
            return false;
        }
    }

    private void endGame() {
        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING! #");
        System.out.println("#######################");
    }

    private void displayPlayerStats() {
        System.out.println("\tYour HP: " + player.getHealth());
    }

    private void displayEnemyStats() {
        System.out.println("\t" + enemy.getName() + "'s HP: " + enemy.getHealth());
    }

    private void gameOver() {
        System.out.println("You limp out of the dungeon, weak from battle.");
        endGame();
    }
}

