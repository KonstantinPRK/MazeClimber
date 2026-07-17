package app.console;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Input {
    private final Scanner scan;


    public Input(Scanner scan) {
        this.scan = scan;
    }


    public Integer readInt() {
        if (scan.hasNextInt()) {
            int value = scan.nextInt();
            scan.nextLine();
            return value;
        } else {
            scan.nextLine();
            return null;
        }
    }
}