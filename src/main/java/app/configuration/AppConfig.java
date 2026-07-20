package app.configuration;

import app.console.Input;
import app.console.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Конфигурационный класс Spring, определяющий бины для ввода-вывода,
 * сканера, потока вывода и генератора случайных чисел.
 *
 * @author unknown
 * @version 1.0
 */
@Configuration
public class AppConfig {

    /**
     * Создаёт бин {@link Input} с переданным сканером.
     *
     * @param systemInScanner бин сканера для чтения из стандартного ввода
     * @return экземпляр {@link Input}
     */
    @Bean
    public Input input(Scanner systemInScanner) {
        return new Input(systemInScanner);
    }


    /**
     * Создаёт бин {@link Scanner} для чтения из стандартного ввода.
     * Метод помечен как {@code destroyMethod = ""}, чтобы Spring не пытался
     * закрыть сканер (это не требуется для {@code System.in}).
     *
     * @return сканер для {@code System.in}
     */
    @Bean(destroyMethod = "")
    public Scanner systemInScanner() {
        return new Scanner(System.in);
    }


    /**
     * Создаёт бин {@link PrintStream} для стандартного вывода.
     *
     * @return {@code System.out}
     */
    @Bean
    public PrintStream systemOut() {
        return System.out;
    }


    /**
     * Создаёт бин {@link Random} для генерации случайных чисел.
     *
     * @return новый экземпляр {@link Random}
     */
    @Bean
    public Random random() {
        return new Random();
    }
}