package app.console;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Input {
    private final Scanner scan;

    public Input(Scanner scan) {
        this.scan = scan;
    }

    public int getUserInt(int min, int max) {
        return getUserInt();
    }

    public int getUserInt() {
        boolean success = false;
        int userInt = -1;

        while (!success) {
            try {
                userInt = scan.nextInt();
                scan.nextLine();
                success = true;
            } catch (Exception e) {
                String print = "Ошибка: это не число. Введите ЦЕЛОЕ число: "; // потом поменяю
                scan.nextLine();
            }
        }
        return userInt;
    }
}