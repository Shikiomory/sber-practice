import tasks.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Для выхода введите 0");

        Scanner scanner = new Scanner(System.in);
        int task;
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите номер задания:");
            task = scanner.nextInt();
            switch (task) {
                case 1:
                    task_1 t1 = new task_1();
                    t1.exec();
                    break;
                case 2:
                    task_2 t2 = new task_2();
                    t2.exec();
                    break;
                case 3:
                    task_3 t3 = new task_3();
                    t3.exec();
                    break;
                case 4:
                    task_4 t4 = new task_4();
                    t4.exec();
                    break;
                case 5:
                    task_5 t5 = new task_5();
                    t5.exec();
                    break;
                case 6:
                    task_6 t6 = new task_6();
                    t6.exec();
                    break;
                case 7:
                    task_7 t7 = new task_7();
                    t7.exec();
                    break;
                case 8:
                    task_8 t8 = new task_8();
                    t8.exec();
                    break;
                case 9:
                    task_9 t9 = new task_9();
                    t9.exec();
                    break;
                case 10:
                    task_10 t10 = new task_10();
                    t10.exec();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }
}