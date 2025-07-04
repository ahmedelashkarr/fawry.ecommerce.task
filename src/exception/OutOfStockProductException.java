package exception;

public class OutOfStockProductException extends RuntimeException{

    public OutOfStockProductException(String message) {
        super(message);
    }
}
