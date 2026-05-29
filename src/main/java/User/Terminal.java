package User;

import java.io.PrintStream;
import java.util.Scanner;

public class Terminal {
    PrintStream printer;
    Scanner scan;

    public Terminal(PrintStream output, Scanner input){
        this.printer = output;
        this.scan = input;
    }


    public void print(String str){
        printer.println(str);
    }


    public int getUserInt(int min, int max){
        String helperPhrase = "введите число от " + min + " до " + max + " включительно: ";
        print(helperPhrase);
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
