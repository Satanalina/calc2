package calc2.exceptions;

public class LimitExecption extends MyRuntimeException{
  public LimitExecption(String message){
    super(message);
  }

  public LimitExecption(String message, LimitExecption ex){
    super(message, ex);
  }
}
