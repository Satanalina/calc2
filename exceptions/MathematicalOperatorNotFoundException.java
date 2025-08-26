package calc2.exceptions;

public class MathematicalOperatorNotFoundException extends MyRuntimeException{
    public MathematicalOperatorNotFoundException(String message){
        super(message);
    }

    public MathematicalOperatorNotFoundException(String message, MathematicalOperatorNotFoundException ex){
        super(message, ex);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
