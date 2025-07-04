package exception;

public class InsufficientCustomerBalanceException extends RuntimeException{

    public InsufficientCustomerBalanceException(String message) {
        super(message);
    }
}
