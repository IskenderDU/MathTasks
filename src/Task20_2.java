public class Task20_2{

    public static double f(double x) {
        return Math.cos(x) - 0.431;
    }

    static class Result {
        double x;
        int steps;

        Result(double x, int steps) {
            this.x = x;
            this.steps = steps;
        }
    }

    // Дихотомия
    public static Result dichotomy(double a, double b, double eps) {
        int steps = 0;
        while ((b - a) / 2 > eps) {
            steps++;
            double c = (a + b) / 2.0;
            if (f(a) * f(c) <= 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return new Result((a + b) / 2.0, steps);
    }

    //Метод Ньютона
    public static Result newton(double x0, double eps) {
        int steps = 0;
        double x = x0;
        while (true) {
            steps++;
            double df = -Math.sin(x); // Производная f'(x)
            double xNext = x - f(x) / df;
            if (Math.abs(xNext - x) < eps) {
                return new Result(xNext, steps);
            }
            x = xNext;
        }
    }

    //Хорд
    public static Result secant(double a, double b, double eps) {
        int steps = 0;
        double xPrev = a;
        double xCurr = b;
        while (true) {
            steps++;
            double xNext = xCurr - f(xCurr) * (xCurr - xPrev) / (f(xCurr) - f(xPrev));
            if (Math.abs(xNext - xCurr) < eps) {
                return new Result(xNext, steps);
            }
            xPrev = xCurr;
            xCurr = xNext;
        }
    }

    // Золотое сечение
    public static Result goldenSection(double a, double b, double eps) {
        int steps = 0;
        double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
        double x1 = b - (b - a) / phi;
        double x2 = a + (b - a) / phi;

        while ((b - a) > eps) {
            steps++;
            if (Math.pow(f(x1), 2) < Math.pow(f(x2), 2)) {
                b = x2;
                x2 = x1;
                x1 = b - (b - a) / phi;
            } else {
                a = x1;
                x1 = x2;
                x2 = a + (b - a) / phi;
            }
        }
        return new Result((a + b) / 2.0, steps);
    }

    public static void main(String[] args) {
        double a = 0.7;
        double b = 1.5;
        double x0 = 0.98;
        double eps = 1e-6;

        Result resD = dichotomy(a, b, eps);
        Result resN = newton(x0, eps);
        Result resS = secant(a, b, eps);
        Result resG = goldenSection(a, b, eps);

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-20s | %-12s | %-15s | %-5s%n", "Метод", "Приближение", "Невязка |f(x)|", "Шаги");
        System.out.println("-------------------------------------------------------------------------");

        printRow("Дихотомия", resD);
        printRow("Ньютон", resN);
        printRow("Хорды", resS);
        printRow("Золотое сечение", resG);

        System.out.println("-------------------------------------------------------------------------");
    }

    private static void printRow(String name, Result res) {
        System.out.printf("%-20s | %-12.6f | %-15.2e | %-5d%n",
                name, res.x, Math.abs(f(res.x)), res.steps);
    }
}