package tasks;

public class task_4 {
    public  void exec() {
        System.out.println("Вывод суммы всех цифр цисла");

        int n = 123, sum = 0;
        int n_copy = n;

        while (n_copy > 0) {
            sum += n_copy % 10;
            n_copy /= 10;
        }

        System.out.println("Сумма цифр числа - " + n + " = " + sum);
    }
}