import java.util.Scanner;

public class GamePlay {
    private Players player; // private field of type Players

private static final int REWARD_AMOUNT = rewardAmount;
private static final int PENALTY_AMOUNT = penaltyAmount;
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a Host and generate the first random number
        Hosts host = new Hosts("Justin", "Host");
        host.randomizeNum();

        // Prompt for player's name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Would you like to enter a last name? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        Players player;
        if (choice.equals("yes") || choice.equals("y")) {
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            player = new Players(firstName, lastName);
        } else {
            player = new Players(firstName);
        }

        // Create Turn object with chosen reward/penalty
        Turn turn = new Turn(REWARD_AMOUNT, PENALTY_AMOUNT);

        boolean keepPlaying = true;

        while (keepPlaying) {
            boolean guessedCorrectly = false;

            // Loop until player guesses correctly
            while (!guessedCorrectly) {
                guessedCorrectly = turn.takeTurn(player, host);
            }

            // Ask if player wants to play again
            System.out.print("Would you like to play again? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();

            if (again.equals("yes") || again.equals("y")) {
                host.randomizeNum(); // generate a new number for next game
            } else {
                keepPlaying = false;
                System.out.println("Thanks for playing! Final status: " + player);
            }
        }

        scanner.close();
    }
}
