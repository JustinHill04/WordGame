public class Phrases {

    private static String gamePhrase = "";
    
    private static String playingPhrase = "";

    
    public static void setGamePhrase(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            System.err.println("Cannot start game with an empty phrase.");
            return;
        }
        
        Phrases.gamePhrase = phrase.trim();
        
        StringBuilder maskedPhrase = new StringBuilder();
        
        for (char c : Phrases.gamePhrase.toCharArray()) {
            if (Character.isLetter(c)) {
                // Replace letters with an underline
                maskedPhrase.append('_');
            } else if (c == ' ') {
                maskedPhrase.append(' ');
            } else {
                // Keep punctuation visible
                maskedPhrase.append(c);
            }
        }
        
        Phrases.playingPhrase = maskedPhrase.toString();
        System.out.println("\nGame started! Current phrase: " + Phrases.playingPhrase);
    }
    
    // Single letter guess with throw exception
    public static void findLetters(String letter) throws MultipleLettersException {
        if (letter == null || letter.length() != 1 || !Character.isLetter(letter.charAt(0))) {
            throw new MultipleLettersException();
        }

        // Convert the phrase and guess to uppercase for case-insensitive comparison
        char guessedChar = Character.toUpperCase(letter.charAt(0));
        String upperGamePhrase = gamePhrase.toUpperCase();
        
        char[] playingChars = playingPhrase.toCharArray();
        boolean foundMatch = false;

        for (int i = 0; i < upperGamePhrase.length(); i++) {
            if (upperGamePhrase.charAt(i) == guessedChar) {
                // Correct letter
                playingChars[i] = gamePhrase.charAt(i);
                foundMatch = true;
            }
        }
        
        playingPhrase = new String(playingChars);

        if (foundMatch) {
            System.out.println("Well done! '" + letter.toUpperCase() + "' found.");
        } else {
            System.out.println("Sorry, '" + letter.toUpperCase() + "' is not in the phrase.");
        }
        
        System.out.println("Current phrase: " + playingPhrase);

        if (!playingPhrase.contains("_")) {
            System.out.println("Congratulations you guessed it! The final phrase was: '" + gamePhrase + "'");
        }
    }
}
