package console;

import java.io.PrintStream;

public class Output {
    private PrintStream printer;


    public Output(PrintStream printer) {
        this.printer = printer;
    }

    public void print(String str){
        printer.println(str);
    }
}
