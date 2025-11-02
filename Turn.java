import java.util.Scanner;
import java.util.Random;

public class Turn {


    int rewardAmount;
    public Turn() {}

    // Player's turn
    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);
        
        // Host prompts the player
        System.out.println(host + " says: " + player.getFirstName() + ", please enter your guess:");
        int guess = scanner.nextInt();
        scanner.nextLine(); 

        String inputLine = "";
        char guessedLetter = ' ';
        boolean validGuess = false;
        boolean letterFound = false;
        
        while (!validGuess) {
            inputLine = scanner.nextLine().trim().toUpperCase();
            
            try {
                // Check for invalid characters (numbers/symbols)
                if (inputLine.isEmpty() || !Character.isLetter(inputLine.charAt(0))) {
                     throw new IllegalArgumentException("Invalid input. Please enter a single letter (A-Z).");
                }
                
                // Check for multiple letters using Phrases.java's logic
                if (inputLine.length() > 1) {
                    throw new MultipleLettersException("You must only enter one letter. Try again.");
                }
                
                guessedLetter = inputLine.charAt(0);
                validGuess = true;
                
            } catch (MultipleLettersException e) {
                // Catching the custom exception for too many letters
                System.out.println("Please enter only one letter.");
                
            } catch (IllegalArgumentException e) {
                // Catching nonletter input 
                System.out.println("Please enter a valid letter (A-Z).");
            }
        }
       try {
            // Find the letter in the phrase and count occurrences
            int occurrences = Phrases.findLetters(guessedLetter);
            
            if (occurrences > 0) {
                letterFound = true;
                System.out.println("Correct! '" + guessedLetter + "' appears " + occurrences + " time(s)!");

                // Calculate winnings
                Random random = new Random();
                int baseReward = rewardAmount * occurrences;
                
                // Determine Prize Type (0 for Money, 1 for Physical)
                int prizeType = random.nextInt(2); 

                if (prizeType == 0) {
                    // Money Prize
                    Award moneyAward = new Money(baseReward); 
                    amountWon = moneyAward.displayWinnings(player, true); 
                } else {
                    // Physical Prize
                    Award physicalAward = new Physical(baseReward); 
                    amountWon = physicalAward.displayWinnings(player, true);
                }
            } else {
                System.out.println("Sorry, '" + guessedLetter + "' is NOT in the phrase.");
            }

        } catch (Exception e) {
            // For other unexpected issues
            System.out.println("An unexpected error occurred during letter evaluation: " + e.getMessage());
        }

        // Update the player's money
        player.setMoney(player.getMoney() + amountWon);

        System.out.println("Player " + player.getFirstName() + "'s current balance: $" + player.getMoney());
        
        return letterFound;
    }

}