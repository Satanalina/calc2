package calc2;


import calc2.exceptions.MathematicalOperatorNotFoundException;

import java.util.Optional;

public class Calculator {

    private int arabicA;
    private int arabicB;
    private String romanA;
    private String romanB;
    private String operation;
    private int res;

    int getRes(){
        return res;
    }

    CheckingTheInput checkingTheInput;

    Calculator(){
        this.checkingTheInput = new CheckingTheInput();
    }

    void initArabicNumbers(String inputStr){
        Optional<String[]> input = checkingTheInput.createArrayFromString(inputStr);

        if(input.isPresent()){
            arabicA = Integer.parseInt(input.get()[0]);
            operation = input.get()[1];
            arabicB = Integer.parseInt(input.get()[2]);
        }
    }

    void initRomanNumbers(String inputStr){
        Optional<String[]> input = checkingTheInput.createArrayFromString(inputStr);

        if(input.isPresent()){
            romanA= input.get()[0];
            operation = input.get()[1];
            romanB = input.get()[2];
        }

        arabicA = Converter.romanToArabic(romanA);
        arabicB = Converter.romanToArabic(romanB);
        calculateAMathematicalExpression();

        if(res < 1){
            throw new MathematicalOperatorNotFoundException("Отрицательное Недопустимое значение");
        }
        String s = Converter.arabicToRoman(res);
    }

    void calculateAMathematicalExpression(){
        res = switch (operation){
            case "+" -> arabicA + arabicB;
            case "-" -> arabicA - arabicB;
            case "/" -> Math.floorDiv(arabicA , arabicB);
            case "*" -> arabicA * arabicB;
            default -> Integer.MIN_VALUE;
        };
    }
}
