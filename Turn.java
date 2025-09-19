import java.util.Scanner;
import java.util.Random;

public class Turn {

    public Turn() {}

    // Player's turn
    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);
        
        // Host prompts the player
        System.out.println(host + " says: " + player.getFirstName() + ", please enter your guess (0-100):");
        int guess = scanner.nextInt();
        scanner.nextLine(); 

        // Check if the guess is correct
        boolean isCorrect = Numbers.compareNumber(guess);

        // Generate a random number to decide the prize type
        Random random = new Random();
        int prizeType = random.nextInt(2); // 0 for Money, 1 for Physical
        
        int amountWon = 0;

        if (prizeType == 0) {
            // Money Prize
            Award moneyAward = new Money(rewardAmount);
            amountWon = moneyAward.displayWinnings(player, isCorrect);
        } else {
            // Physical Prize
            Award physicalAward = new Physical();
            amountWon = physicalAward.displayWinnings(player, isCorrect);
        }
        
        // Update the player's money
        player.setMoney(player.getMoney() + amountWon);

        System.out.println(player);
        
        return isCorrect;
    }
}