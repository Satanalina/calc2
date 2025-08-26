package calc2;

public class Converter {
    int a = 13;

    enum Roman {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);
        private final int n;

        Roman(int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }

        @Override
        public String toString() {
            return "Roman{" +
                    "n=" + n +
                    '}';
        }
    }

    enum CombinationOfSubtraction {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        private final int n;

        CombinationOfSubtraction(int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }

        @Override
        public String toString() {
            return "CombinationOfSubtraction{" +
                    "n=" + n +
                    '}';
        }

        public static CombinationOfSubtraction[] getEnumArr() {
            return new CombinationOfSubtraction[]{M, CM, D, CD, C, XC, L, XL, X, IX, V, IV, I};
        }
    }



    static String arabicToRoman(int arabicNumberAsInt) {
        //Комбинации вычитания римских цифр
        StringBuilder roman = new StringBuilder();
        final CombinationOfSubtraction[] combSubstr = CombinationOfSubtraction.values();

        for (int i = 0; i < combSubstr.length; i++) {
            while (arabicNumberAsInt >= combSubstr[i].getN()) {
                roman.append(combSubstr[i].name());
                arabicNumberAsInt -= combSubstr[i].getN();
            }
        }
        return roman.toString();
    }


    static int romanToArabic(String romanNumberAsString) {

        char[] romans = romanNumberAsString.toCharArray();
        int result = 0;
        int arrLen = romans.length - 1;
        int prevRomanNumber = 0;

        for (int i = arrLen; i >= 0; i--) {

            final Roman currentRomanNumber = Roman.valueOf(String.valueOf(romans[i]));
            final int currentDecimalValueOfARomanNumber = currentRomanNumber.getN();

            if (currentDecimalValueOfARomanNumber < prevRomanNumber) {
                result -= currentDecimalValueOfARomanNumber;
            } else {
                result += currentDecimalValueOfARomanNumber;
            }
            prevRomanNumber = currentDecimalValueOfARomanNumber;
        }
        return result;
    }
}
