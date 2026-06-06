package console;

import java.util.Scanner;

public class Input {
    private Scanner scan;

    public Input(Scanner scan) {
        this.scan = scan;
    }

    public int getUserInt(int min, int max){
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
                print("Ошибка: это не число. Введите ЦЕЛОЕ число: ");
                scan.nextLine();
            }
        }

        return userInt;
    }
}
