import java.util.Scanner;

public class GamePlay {
    private Players[] currentPlayers;

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GamePlay game = new GamePlay();

        // Get reward and penalty amounts
        System.out.println("Reward amount for correct guess: ");
        int rewardAmount = scanner.nextInt();
        System.out.println("Penalty for incorrect guess: ");
        int penaltyAmount = scanner.nextInt();
        scanner.nextLine();

        final int REWARD_AMOUNT = rewardAmount;
        final int PENALTY_AMOUNT = penaltyAmount;

        // Create a Host and generate the first random number
        Hosts host = new Hosts("Justin", "Host");
        host.randomizeNum();

        // Populate array with 3 players and assign names
        game.currentPlayers = new Players[3];
        for (int i = 0; i < game.currentPlayers.length; i++){
            System.out.println("\nPlayer " + (i+1) + ":");
    
        
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
        }

        // Create Turn object with chosen reward/penalty
        Turn turn = new Turn(REWARD_AMOUNT, PENALTY_AMOUNT);

        boolean keepPlaying = true;

        while (keepPlaying) {
            boolean guessedCorrectly = false;
            int currentPlayerIndex = 0;

            // Loop until player guesses correctly
            while (!guessedCorrectly) {
                Players currentPlayers = game.currentPlayers[currentPlayerIndex];
                System.out.println("\nIt's " + currentPlayers.getFirstName() + "'s turn.");
                guessedCorrectly = turn.takeTurn(currentPlayers, host);

                //Move to next player
                if (!guessedCorrectly) {
                    currentPlayerIndex = (currentPlayerIndex + 1) % game.currentPlayers.length;
                }
            }

            // Ask if player wants to play again
            System.out.print("Would you like to play again? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();

            if (again.equals("yes") || again.equals("y")) {
                host.randomizeNum(); // generate a new number for next game
            } else {
                keepPlaying = false;
                System.out.println("Thanks for playing! Final results: ");
                for (Players p : game.currentPlayers) {
                    System.out.println(p);
                }
            }
        }

        scanner.close();
    }
}
