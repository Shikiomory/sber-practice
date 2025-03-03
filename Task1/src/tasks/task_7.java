package tasks;

import java.util.Scanner;

public class task_7 {
    public void exec() {
        System.out.println("Вывод n первых чисел фибоначи\nВведите n: ");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int prev_number = 0;
        int number = 1;
        int FibonacсiNumber = number;

        for (int i = 0; i < n; ++i) {
            System.out.print(FibonacсiNumber + " ");
            FibonacсiNumber = prev_number + number;
            prev_number = number;
            number = FibonacсiNumber;
        }

        System.out.println();
    }
}