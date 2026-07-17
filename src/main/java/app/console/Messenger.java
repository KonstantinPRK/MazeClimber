package app.console;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class Messenger {
    private Terminal terminal;
    private final String MEANING_IS_NOT_EXIST = "значение отсутствует";


    public Messenger(Terminal terminal){
        this.terminal = terminal;
    }


    public void showInformation(String... text){
        StringBuilder stringBuilder = new StringBuilder();
        for(String line : text) stringBuilder.append(line).append("\n");
        terminal.printSystemDescription(stringBuilder.toString());
    }


    public void applyCurrentOptions(String optionName, String SEPARATOR, String optionValue) {
        if((optionName == null) || (optionValue == null)) throw new NullPointerException(MEANING_IS_NOT_EXIST);
        terminal.printUserParameter(optionName, SEPARATOR, optionValue);
    }


    public String requestRespond(String... options){
        terminal.printNumberedOptions(options);
        int userChoice = terminal.getUserInt(1, options.length);
        return options[userChoice - 1];
    }

    public String requestRespond(List<String> catalog){
        terminal.printNumberedOptions(catalog.toArray(String[]::new));
        int userChoice = terminal.getUserInt(1, catalog.size());
        return catalog.get(userChoice - 1);
    }

    public int requestRespond(int min, int max){
        return terminal.getUserInt(min, max);
    }


    public void unformattedPrint(String... unformattedText) {
        terminal.unformattedPrint(unformattedText);
    }
}
