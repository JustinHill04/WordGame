public class MultipleLettersException extends Exception {

    public MultipleLettersException() {
        super();
    }

    @Override
    public String getMessage() {
        return "More than one letter was entered";
    }
}