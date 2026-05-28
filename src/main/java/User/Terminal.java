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


    public void print(Object object){
        printer.println(object);
    }

    public int getUserInt(){
        return 0;
    }

    public int getUserInt(int min, int max) {
        String helperPhrase = "введите число от " + min + " до " + max + " включительно: ";
        boolean success = false;
        int userInt = -1;

        print(helperPhrase);
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
