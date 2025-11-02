public class Players extends Person {
    private int money; // private instance variable for current money

    // Constructor (with only first name)
    public Players(String firstName) {
        super(firstName); // call Person constructor
        this.money = 1000; // initialize with default amount
    }

    // Constructor (with first name and last name)
    public Players(String firstName, String lastName) {
        super(firstName, lastName); // call Person constructor
        this.money = 1000; // initialize with default amount
    }

    // Getter for money
    public int getMoney() {
        return money;
    }

    // Setter for money
    public void setMoney(int money) {
        this.money = money;
    }

    // Override toString to show name + money
    @Override
    public String toString() {
        return super.toString() + " | Money: $" + money;
    }
}
