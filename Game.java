public class Person.java {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(String firstName) {
        this.firstName = firstName;
        this.lastName = ""; 
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

public class Numbers.java {
    private int randomNum;

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public void generateNumber() {
        Random rand = new Random();
        this.randomNum = rand.nextInt(101);

    }

    public boolean compareNumber(int guess) {
        if (guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!")
            return true;
        } else if (guess > randomNnum) {
            System.out.println("I'm sorry. That guess was too high.");
            return false;
        } else {
            System.out.println("I'm sorry. That guess was too low.");
            return false;
        }
    }
}

public class GamePlay.java {

    private Person;

    public static void main(String[] args) {
       let firstName = prompt("First Name: ");
       let lastNameQuestion = prompt("Would you like to enter a last name? (yes/no)");

       if (lastNameQuestion == "yes") {
        let lastName = prompt("Last Name: ");
        player = new Person(firstName, lastName);
       } else {
        player = new Person(firstName);
       }
    }
    Numbers numbers = new Numbers();
    numbers.generateNumber();

    boolean guessCorrect = false;

    while (!guessCorrect) {
        int guess = prompt(player.getFirstName() + ", enter your guess: ");
        guessCorrect = numbers.compareNumber(guess);

    }
    prompt("Congratulations " + player.getFirstName() + "! You guessed the number!");
}

