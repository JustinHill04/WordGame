import java.util.Scanner;

public class Hosts extends Person {

    private static final Scanner scanner = new Scanner(System.in);

    // Get game phrase
    private void setGamePhrase() {
        System.out.println("Host " + this + ", please enter the phrase for the players to guess:");
        
        String phrase = scanner.nextLine(); 
        
        // Store the phrase
        Phrases.gamePhrase = phrase.toUpperCase(); 
        System.out.println("New Phrase! The length is " + Phrases.gamePhrase.length() + " characters.");
    }

    // Only first name
    public Hosts(String firstName) {
        super(firstName);
        setGamePhrase(); 
    }

    // First and last name
    public Hosts(String firstName, String lastName) {
        super(firstName, lastName);
        setGamePhrase();
    }
    
    // Play again and set new phrase
    public void setNewGamePhrase() {
        System.out.println("The players are starting a new game.");
        setGamePhrase(); 
    }
}