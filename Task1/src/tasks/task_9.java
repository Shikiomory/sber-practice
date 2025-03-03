package tasks;

public class task_9 {
    public void exec() {
        System.out.println("Решение квадратного уравнения");

        double a, b, c;
        double d;
        double x1, x2;

        a = 2; b = 3; c = 1;
        d = b * b - 4 * a * c;

        if (d < 0) {
            System.out.println("Корней нет");
        }
        else if (d == 0) {
            x1 = -b / (2 * a);
            System.out.println("x1 = x2 = " + x1);
        }
        else {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("x1 = " + x1 + " x2 = " + x2);
        }
    }
}