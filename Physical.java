import java.util.Random;

public class Physical implements Award {

    private String[] prizes = {
        "a brand new car",
        "a tropical vacation",
        "a home entertainment system",
        "a Rolex watch",
        "a new patio furniture set"
    };
    // Gets a prize from the options
    private int getRandomPrize() {
        Random rand = new Random();
        return rand.nextInt(prizes.length);
    }

    public int displayWinnings(Players player, boolean isCorrect) {
        String prize = prizes[getRandomPrize()];

        if (isCorrect) {
            System.out.println("Congratulations, " + player.getFirstName() + "! You won " + prize + "!");
        } else {
            System.out.println("Sorry, " + player.getFirstName() + ". You lost. You could have won " + prize + ".");
        }
        return 0;
    }
}