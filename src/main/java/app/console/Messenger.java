package app.console;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Компонент-посредник между пользовательским интерфейсом (терминалом) и логикой приложения.
 * Предоставляет унифицированные методы для вывода информационных сообщений,
 * отображения текущих параметров, запроса выбора из списка и получения целого числа.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class Messenger {
    private Terminal terminal;
    private final String MEANING_IS_NOT_EXIST = "значение отсутствует";


    /**
     * Конструктор, инициализирующий терминал для взаимодействия с пользователем.
     *
     * @param terminal компонент терминала, обеспечивающий ввод/вывод
     */
    public Messenger(Terminal terminal) {
        this.terminal = terminal;
    }


    /**
     * Выводит информационное сообщение, состоящее из одной или нескольких строк.
     * Строки объединяются через перенос строки и выводятся системным описанием.
     *
     * @param text одна или несколько строк сообщения
     */
    public void showInformation(String... text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : text) stringBuilder.append(line).append("\n");
        terminal.printSystemDescription(stringBuilder.toString());
    }


    /**
     * Отображает текущее значение параметра (опции) в формате "имя: значение".
     * Параметр и разделитель выделяются жирным шрифтом.
     * Если {@code optionName} или {@code optionValue} равны {@code null},
     * выбрасывается {@link NullPointerException} с сообщением об отсутствии значения.
     *
     * @param optionName   название параметра
     * @param SEPARATOR    строка-разделитель между названием и значением
     * @param optionValue  значение параметра
     * @throws NullPointerException если optionName или optionValue равны null
     */
    public void applyCurrentOptions(String optionName, String SEPARATOR, String optionValue) {
        if ((optionName == null) || (optionValue == null)) throw new NullPointerException(MEANING_IS_NOT_EXIST);
        terminal.printUserParameter(optionName, SEPARATOR, optionValue);
    }


    /**
     * Запрашивает у пользователя выбор одного из переданных вариантов.
     * Варианты нумеруются, пользователь вводит номер.
     *
     * @param options массив строк-вариантов для выбора
     * @return выбранная строка из массива
     */
    public String requestRespond(String... options) {
        terminal.printNumberedOptions(options);
        int userChoice = terminal.getUserInt(1, options.length);
        return options[userChoice - 1];
    }


    /**
     * Запрашивает у пользователя выбор одного из вариантов, переданных в виде списка.
     * Варианты нумеруются, пользователь вводит номер.
     *
     * @param catalog список строк-вариантов для выбора
     * @return выбранная строка из списка
     */
    public String requestRespond(List<String> catalog) {
        terminal.printNumberedOptions(catalog.toArray(String[]::new));
        int userChoice = terminal.getUserInt(1, catalog.size());
        return catalog.get(userChoice - 1);
    }


    /**
     * Запрашивает у пользователя ввод целого числа в заданном диапазоне.
     * Диапазон включительный.
     *
     * @param min минимальное допустимое значение
     * @param max максимальное допустимое значение
     * @return корректное целое число, введённое пользователем
     */
    public int requestRespond(int min, int max) {
        return terminal.getUserInt(min, max);
    }


    /**
     * Печатает переданные строки без форматирования, разделяя каждую пустой строкой.
     *
     * @param unformattedText строки для вывода
     */
    public void unformattedPrint(String... unformattedText) {
        terminal.unformattedPrint(unformattedText);
    }
}