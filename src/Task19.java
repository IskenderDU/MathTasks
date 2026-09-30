public class Task19 {
    public static void main(String[] args) {
        double x = 0.4;
        double eps = 1e-8; // 10^(-8)

        double x2 = x * x; // x в квадрате
        double t = 1.0;    // первый член ряда (при k = 0)
        double sum = 1.0;  // сумма ряда
        int k = 0;         // счет итераций
        int count = 1;     // количество учтенных членов

        while (Math.abs(t) >= eps) {
            t = -t * x2 / ((2 * k + 1) * (2 * k + 2));
            sum = sum + t;
            k = k + 1;
            count = count + 1;
        }

        System.out.println("Сумма cos(x): " + sum);
        System.out.println("Число членов: " + count);
    }
}