package calc2;


import calc2.exceptions.LimitExecption;
import calc2.exceptions.MathematicalOperatorNotFoundException;
import calc2.exceptions.MyRuntimeException;
import calc2.exceptions.OperandMismatchException;

import java.util.Arrays;
import java.util.Scanner;

public class RunCalc {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        System.out.println("""
                Калькулятор римских либо арабских чисел от 1 до 10
                цифры должны соответствовать друг другу
                пример ввода
                X - V
                10 - 5
                Для выхода набрать ВЫХОД
                """);

        String input = null;

        while (true) {
            input = scanner.nextLine();
           if (input.equals("ВЫХОД")) {
               System.out.println("Закончить вычисления");
               break;
            }
          //  boolean isMathOperationIsCorrect = false;
            String arabianOrRomanOperands = "";
            try {

                try {
            //        isMathOperationIsCorrect = calculator.checkingTheInput.checkMathematicalOperation(input);
                    arabianOrRomanOperands = calculator.checkingTheInput.match3(input);

                } catch (MathematicalOperatorNotFoundException | OperandMismatchException exx) {

                    if (exx instanceof MathematicalOperatorNotFoundException e) {

                        throw new MathematicalOperatorNotFoundException("Отстствует оператор", e);
                    } else {

                        OperandMismatchException e = (OperandMismatchException) exx;
                        throw new OperandMismatchException("Некорректные операторы", e);
                    }
                }

                if (arabianOrRomanOperands.equals("roman")) {

                    calculator.initRomanNumbers(input);
                    limitNumberOfTen(calculator.getRomanA(), calculator.getRomanB());
                    System.out.println("= " + Converter.arabicToRoman(calculator.getRes()));
                } else if (arabianOrRomanOperands.equals("arabian")) {

                    calculator.initArabicNumbers(input);
                    limitNumberOfTen(calculator.getArabicA(), calculator.getArabicB());
                    calculator.calculateAMathematicalExpression();
                    System.out.println("= " + calculator.getRes());
                } else {
                    System.out.println("Пустая строка");
                }
            } catch (RuntimeException ex) {
                System.out.println("Текст исключения: " + ex.getMessage());
                System.out.println("Причина исключения: " + ex.getCause());
                System.out.println("Ex");
                ex.printStackTrace();
                throw new RuntimeException("Exit: " + ex.getMessage());
            }
        }
    }

    private static void limitNumberOfTen(int a, int b){
        if (a < 1 || a > 10 || b < 1 || b > 10){
            throw new LimitExecption("Числа должны быть от 1 до 10");
        }
    }

    private static void limitNumberOfTen(String a, String b){
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII","IX", "X"};
        if(!Arrays.asList(roman).contains(a) || !Arrays.asList(roman).contains(b) ){
            throw new LimitExecption("Числа должны быть от I до X");
        }

    }

}
