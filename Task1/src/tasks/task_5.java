package tasks;

import java.util.Scanner;

public class task_5 {
    public void exec() {
        System.out.print("Вычисление веса на луне\nВведите вес на земле: ");

        Scanner scanner = new Scanner(System.in);
        float weight_Earth, weight_Moon;

        weight_Earth = scanner.nextFloat();
        weight_Moon = weight_Earth * 0.17f;

        System.out.println("Вес на луне: " + weight_Moon);
    }
}