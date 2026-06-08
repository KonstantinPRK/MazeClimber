package app.console;

import org.springframework.stereotype.Component;
import java.io.PrintStream;

@Component
public class Output {
    private final PrintStream printer;

    public Output(PrintStream printer) {
        this.printer = printer;
    }

    public void print(String str) {
        printer.println(str);
    }
}