package tasks;

import java.util.Random;
import java.util.Scanner;

public class task_8 {
    public  void exec() {
        System.out.println("Угадывание загаданной буквы\nВведите свою букву: ");

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        char letter = (char)(random.nextInt(25) + 65);
        boolean guess = false;
        char user_letter;

        System.out.println(letter);

        while(!guess) {
            user_letter = scanner.next().charAt(0);
            if (letter == user_letter) {
                guess = true;
                System.out.println("Right");
            }
            else {
                System.out.println((user_letter < letter) ? "You’re too low" : "You're too high");
            }
        }
    }
}