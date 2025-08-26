package calc2;


import calc2.exceptions.MathematicalOperatorNotFoundException;
import calc2.exceptions.MyRuntimeException;
import calc2.exceptions.OperandMismatchException;

import java.util.Scanner;

public class RunCalc {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        System.out.println("""
                Калькулятор римских либо арабских чисел
                цифры должны соответствовать друг другу
                пример ввода
                XXX - XX
                30 - 60
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
                    System.out.println("= " + Converter.arabicToRoman(calculator.getRes()));
                } else if (arabianOrRomanOperands.equals("arabian")) {

                    calculator.initArabicNumbers(input);
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
}
