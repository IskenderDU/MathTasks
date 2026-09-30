public class Task20 {
    public static void main(String[] args) {
        double tVal = 0.41;
        double eps = 1e-6; // 10^(-6)

        // Вычислить exp(t)
        double expSum = 0.0;
        double termExp = 1.0;
        int kExp = 0;
        int countExp = 0;

        while (Math.abs(termExp) >= eps) {
            expSum = expSum + termExp;
            countExp = countExp + 1;
            kExp = kExp + 1;
            // Каждый след член exp (t) = прошл член * t / k
            termExp = termExp * tVal / kExp;
        }

        // Вычислить cos(t)
        double cosSum = 0.0;
        double termCos = 1.0;
        int kCos = 0;
        int countCos = 0;
        double tSquare = tVal * tVal;

        while (Math.abs(termCos) >= eps) {
            cosSum = cosSum + termCos;
            countCos = countCos + 1;
            // Рекуррентная формула для cos(t)
            termCos = -termCos * tSquare / ((2 * kCos + 1) * (2 * kCos + 2));
            kCos = kCos + 1;
        }

        // Итог
        double fApprox = expSum * cosSum;
        double fExact = Math.exp(tVal) * Math.cos(tVal);
        double error = Math.abs(fApprox - fExact);

        System.out.println("Приближенное F(t): " + fApprox);
        System.out.println("Число членов для exp: " + countExp);
        System.out.println("Число членов для cos: " + countCos);
        System.out.println("Погрешность: " + error);
    }
}