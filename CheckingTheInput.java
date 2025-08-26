package calc2;
import calc2.exceptions.MathematicalOperatorNotFoundException;
import calc2.exceptions.MyRuntimeException;
import calc2.exceptions.OperandMismatchException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CheckingTheInput {
    final static String pattenOfMatchingAStringToANumber = "-?\\d+(\\.\\d+)?";
    final static String PATTERN_OF_MATCHING_A_STRING_TO_A_ROMAN_NUMBER = "^[IVXLCDM]+$";

    String match3(String inputString){

        final Optional<String[]> clrArray =  createArrayFromString(inputString);

        if(clrArray.isPresent()) {
            final String[] clearedOfSpacesArray = clrArray.get();

            String op1 = null, op2 = null;

            if (clearedOfSpacesArray[0].matches(pattenOfMatchingAStringToANumber)) {
                op1 = "arabian";
            } else if (clearedOfSpacesArray[0].matches(PATTERN_OF_MATCHING_A_STRING_TO_A_ROMAN_NUMBER)) {
                op1 = "roman";
            }
            if (clearedOfSpacesArray[2].matches(pattenOfMatchingAStringToANumber)) {
                op2 = "arabian";
            } else if (clearedOfSpacesArray[2].matches(PATTERN_OF_MATCHING_A_STRING_TO_A_ROMAN_NUMBER)) {
                op2 = "roman";
            }


            if (Objects.isNull(op1)) {
                throw new OperandMismatchException("Первая цифра имеет недопустимый формат. Нельзя смешивать арабские и римские цифры");
              //  return "op1 is null";
            } else if (Objects.isNull(op2)) {
                throw new OperandMismatchException("Вторая цифра имеет недопустимый формат. Нельзя смешивать арабские и римские цифры");
              //  return "op2 is null";
            }
            if (op1.equals(op2)) {
                return op1;
            } else {
                throw new OperandMismatchException("Обе цифры должны быть либо римскими числами, либо арабскими", new OperandMismatchException("Объект исключения"));
            }
        }else{
            return "empty";
        }
    }

    Optional<String[]> createArrayFromString(String input){
        if(input.isBlank()){
            throw new MyRuntimeException("Пустая строка ввода");
        }
        String[] source = input.trim().split(" ");

        int destinationArrLength = 0;
        for (String s : source) {
            if (!s.isBlank()) {
                destinationArrLength++;
            }
        }

        String[] destinationArr = null;
        Optional<String[]> oArr = Optional.empty();
        
        if(destinationArrLength > 3){
            throw new RuntimeException("Слишком много операндов");
        }else if(destinationArrLength < 3){
            throw new RuntimeException("Слишком мало операндов");
        }else{
            destinationArr = new String[destinationArrLength];
            oArr = Optional.of(destinationArr);
            
            for(int i = 0, j = 0; i < source.length; i++){
                if(!source[i].isBlank()){
                    if(oArr.isPresent()){
                        oArr.get()[j] = source[i];
                    }
                    destinationArr[j] = source[i];
                    j++;
                }
            }
        }

        if(oArr.isPresent()){
            if(!checkArray(oArr.get()[0]) && checkArray(oArr.get()[1]) && !checkArray(oArr.get()[2]) ){
                return oArr;
            } else{
                return Optional.empty();
            }
        }
        return  Optional.empty();
    }

    private boolean checkArray(String o){
        return o.equals("-") || o.equals("+") || o.equals("/") || o.equals("*");
    }


    String[] clearArrayOfWhiteSpaces(String input){
        String[] ar = input.trim().split(" ");


        String operator = "";
        for(int i =ar.length-1, j = 1; i > 1 ; i--, j++){

            if(!ar[i].isEmpty() && !ar[i].equals(operator)){
                operator = ar[i];
                ar[j] = ar[i];
            }

            ar[i] = " ";
            if( i == 2){
                ar[i] = ar[i].trim();
                ar[i] = operator;
            }
        }
        String[] newArr = new String[3];

        System.arraycopy(ar, 0, newArr, 0, 3);
        return newArr;
    }

    private String checkMathematicalOperationHelper(String input, String oper){
        String newStr = "";
        String operator = input.substring(input.indexOf(oper), input.indexOf(oper)+1 );
        newStr+= input.substring(0, input.indexOf(operator));
        newStr += input.substring(input.indexOf(operator)+1);
        return newStr;
    }

    private boolean initialVerificationOfMathOperator(String input, String operator) throws RuntimeException{
        String newStAfterOperator =  input.substring(input.indexOf(operator));
        String newStBeforeOperator =  input.substring(0,input.indexOf(operator) + 1);
        String newStr = checkMathematicalOperationHelper(input, operator);
        boolean isCorrect = false;

        if(newStr.contains(operator)){
            throw new RuntimeException("Удвоение оператора");
        }
        else if ( input.length() == 1 ||
                  (   newStAfterOperator.contains(operator) && newStAfterOperator.charAt(1) != 32)   ) {
            throw new RuntimeException("+A");
        }
        else if ( newStBeforeOperator .contains(operator) &&
                newStBeforeOperator.charAt(newStBeforeOperator.length() - 2) != 32) {
            throw new RuntimeException("B+");
        }
        else{
            isCorrect = true;
        }
        return isCorrect;
    }

    boolean checkMathematicalOperation(String input) throws MathematicalOperatorNotFoundException {
        boolean isCorrect = false;
        if(input.contains("*")){
            isCorrect = initialVerificationOfMathOperator(input, "*");
        }
        else if(input.contains("+") ){
            isCorrect = initialVerificationOfMathOperator(input, "+");
        }
        else if(input.contains("-")){
            isCorrect = initialVerificationOfMathOperator(input, "-");
        }else if(input.contains("/")){
            isCorrect = initialVerificationOfMathOperator(input, "/");
        }
        else{
            throw new MathematicalOperatorNotFoundException("МАТЕМАТИЧЕСКИЙ ОПЕРАТОР ОТСУТСТВУЕТ", new MathematicalOperatorNotFoundException("Объект исключения"));
        }
        return isCorrect;
    }
}
