public class Hosts extends Person {

    // Only first name
    public Hosts(String firstName) {
        super(firstName);
    }

    // First and last name
    public Hosts(String firstName, String lastName) {
        super(firstName, lastName);
    }

    // Public method to randomize the number
    public void randomizeNum() {
        Numbers.generateNumber(); 
        System.out.println("Host " + this + " has generated a random number!");
    }
}
