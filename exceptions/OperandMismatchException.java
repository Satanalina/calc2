package calc2.exceptions;

public class OperandMismatchException extends MyRuntimeException{
    public OperandMismatchException(String message){
        super(message);
    }

    public OperandMismatchException(String message, OperandMismatchException ex){
        super(message, ex);
    }
}
