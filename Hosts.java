public class Hosts extends Person {

    // Constructor (with only first name)
    public Hosts(String firstName) {
        super(firstName);
    }

    // Constructor (with first name and last name)
    public Hosts(String firstName, String lastName) {
        super(firstName, lastName);
    }

    // Public method to randomize the number
    public void randomizeNum() {
        Numbers.generateNumber(); // uses static method from Numbers
        System.out.println("Host " + this + " has generated a random number!");
    }
}
