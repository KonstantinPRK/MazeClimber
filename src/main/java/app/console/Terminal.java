package app.console;

import org.springframework.stereotype.Component;

/**
 * Компонент для работы с терминалом, объединяющий возможности форматирования,
 * вывода и ввода данных. Предоставляет методы для печати с форматированием,
 * вывода нумерованных списков и получения целых чисел от пользователя.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class Terminal {
    private final Editor edit;
    private final Output output;
    private final Input input;
    String SEPARATOR = ": ",
            ERROR = "Ошибка",
            IntegerOutOfRange = "число вне диапазона. ",
            notAnInteger = "введено не целое число. ",
            offerToEnterNumber = "Введите число ",
            from = "от ",
            to = "до ",
            inclusive = "включительно",
            TryAgain = "Попробуйте снова. ";


    /**
     * Конструктор, инициализирующий компоненты для редактирования, вывода и ввода.
     *
     * @param edit   компонент для форматирования текста
     * @param output компонент для вывода данных
     * @param input  компонент для чтения ввода пользователя
     */
    public Terminal(Editor edit, Output output, Input input) {
        this.edit = edit;
        this.output = output;
        this.input = input;
    }


    /**
     * Печатает переданные строки без дополнительного форматирования,
     * разделяя каждую строку одной пустой строкой.
     *
     * @param text строки для вывода
     */
    public void unformattedPrint(String... text) {
        for (String line : text) {
            output.print(line);
            output.printEmptyLines(1);
        }
    }


    /**
     * Печатает описание системы с одной пустой строкой после него.
     *
     * @param description текст описания
     */
    public void printSystemDescription(String description) {
        output.print(description);
        output.printEmptyLines(1);
    }


    /**
     * Печатает параметр пользователя в формате "имя: значение".
     * Имя и разделитель выделяются жирным шрифтом.
     *
     * @param optionName   название параметра
     * @param SEPARATOR    строка-разделитель между названием и значением
     * @param optionValue  значение параметра
     */
    public void printUserParameter(String optionName, String SEPARATOR, String optionValue) {
        output.print(
                edit.bold(optionName),
                edit.bold(SEPARATOR),
                optionValue
        );

        output.printEmptyLines(0);
    }


    /**
     * Печатает нумерованный список опций. Каждая опция выводится с порядковым номером,
     * разделённым двоеточием. После списка добавляется одна пустая строка.
     *
     * @param options массив строк-опций
     */
    public void printNumberedOptions(String... options) {
        for (int number = 1; number <= options.length; number++) {
            output.print(
                    edit.bold(String.valueOf(number)),
                    SEPARATOR,
                    options[number - 1]
            );

            output.printEmptyLines(0);
        }

        output.printEmptyLines(1);
    }


    /**
     * Запрашивает у пользователя целое число в заданном диапазоне.
     * Выводит приглашение с указанием границ. При вводе нецелого числа или
     * числа вне диапазона выводится сообщение об ошибке и запрос повторяется.
     *
     * @param min минимальное допустимое значение (включительно)
     * @param max максимальное допустимое значение (включительно)
     * @return корректное целое число, введённое пользователем
     */
    public int getUserInt(int min, int max) {
        output.print(edit.bold(offerToEnterNumber + from + min + " " + to + max + " " + inclusive + SEPARATOR));
        output.printEmptyLines(0);

        while (true) {
            Integer userInt = input.readInt();
            output.printEmptyLines(0);

            if (userInt == null) {
                output.print(edit.frame(ERROR + SEPARATOR + notAnInteger + TryAgain));
                output.printEmptyLines(0);
                continue;
            }

            if (userInt >= min && userInt <= max) {
                return userInt;
            }

            output.print(edit.frame(ERROR + SEPARATOR + IntegerOutOfRange + TryAgain));
            output.printEmptyLines(0);
        }
    }
}