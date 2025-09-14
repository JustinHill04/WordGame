import java.util.Scanner;

public class Turn {
    private int rewardAmount;   // amount added when player guesses correctly
    private int penaltyAmount;  // amount subtracted when player guesses incorrectly

    // Constructor lets you set reward and penalty amounts
    public Turn(int rewardAmount, int penaltyAmount) {
        this.rewardAmount = rewardAmount;
        this.penaltyAmount = penaltyAmount;
    }

    // Handles a single player's turn
    // Returns true if guess is correct, false otherwise
    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);

        // Host prompts the player
        System.out.println(host + " says: " + player + ", please enter your guess (0–100):");
        int guess = scanner.nextInt();

        // Check guess with Numbers
        if (Numbers.compareNumber(guess)) {
            // Correct guess -> increase money
            player.setMoney(player.getMoney() + rewardAmount);
            System.out.println("Congratulations " + player.getFirstName() + "! You guessed the number!");
            System.out.println(player);
            return true;
        } else {
            // Incorrect guess -> decrease money
            player.setMoney(player.getMoney() - penaltyAmount);
            System.out.println("Wrong guess, " + player.getFirstName() + ".");
            System.out.println(player);
            return false;
        }
    }
}
