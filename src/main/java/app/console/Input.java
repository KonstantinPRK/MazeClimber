package app.console;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Input {
    private final Scanner scan;
    private final Output output;

    public Input(Scanner scan, Output output) {
        this.scan = scan;
        this.output = output;
    }

    public int getUserInt(int min, int max) {
        while (true) {
            output.print(output.bold("Введите число от " + min + " до " + max + " включительно: "));
            int userInt = readInt();
            if (userInt >= min && userInt <= max) {
                return userInt;
            }
            output.printFramed("Ошибка: число вне диапазона. Попробуйте снова.");
        }
    }

    private int readInt() {
        while (true) {
            try {
                int value = scan.nextInt();
                scan.nextLine();
                return value;
            } catch (Exception e) {
                output.printFramed("Ошибка: введено не целое число. Попробуйте снова.");
                scan.nextLine();
            }
        }
    }
}