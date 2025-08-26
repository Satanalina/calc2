package calc2.exceptions;

public class MyRuntimeException  extends RuntimeException{
    String myMessage = "";

    public MyRuntimeException(String message) {
        super(message);
        this.myMessage = message;
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.myMessage = message;
    }
}
