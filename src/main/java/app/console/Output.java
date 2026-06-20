package app.console;

import org.springframework.stereotype.Component;
import java.io.PrintStream;
import java.util.List;

@Component
public class Output {
    private final PrintStream printer;

    public Output(PrintStream printer) {
        this.printer = printer;
    }

    public void print(String str) {
        printer.println(str);
    }

    public void printEmptyLines(int count) {
        for (int i = 0; i < count; i++) printer.println();
    }

    public void printCatalog(List<String> catalog) {
        for (int count = 1; count <= catalog.size(); count++) {
            String item = catalog.get(count - 1);

            String formatted;
            int dashIndex = item.indexOf(" - ");
            if (dashIndex != -1) {
                String name = item.substring(0, dashIndex);
                String description = item.substring(dashIndex + 3);
                formatted = count + ". " + bold(name) + " - " + description;
            } else {
                formatted = count + ". " + bold(item);
            }

            print(formatted);
        }
    }

    public void printFramed(String message) {
        String border = "═".repeat(message.length() + 2);
        print("╔" + border + "╗");
        print("║ " + message + " ║");
        print("╚" + border + "╝");
    }

    public String bold(String text) {
        return "\033[1m" + text + "\033[0m";
    }
}