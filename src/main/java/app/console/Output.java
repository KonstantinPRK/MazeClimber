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

    public void printCatalog(List<String> catalog){
        for(int count = 1; count <= catalog.size(); count++){
            String numberOfPosition = count + ".";
            String variantName = " " + catalog.get(count - 1);
            print(numberOfPosition + variantName);
        }
    }
}