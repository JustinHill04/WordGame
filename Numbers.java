import java.util.Random;

public class Numbers {
    private int randomNum;

    // Getter
    public int getRandomNum() {
        return randomNum;
    }

    // Setter
    @SuppressWarnings("static-access")
    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    // Generate random number between 0 and 100
    @SuppressWarnings("static-access")
    public void generateNumber() {
        Random rand = new Random();
        this.randomNum = rand.nextInt(101); // 0–100 inclusive
    }

    // Compare guess with randomNum
    public boolean compareNumber(int guess) {
        if (guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        } else if (guess > randomNum) {
            System.out.println("I'm sorry. That guess was too high.");
            return false;
        } else {
            System.out.println("I'm sorry. That guess was too low.");
            return false;
        }
    }
}