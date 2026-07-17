package app.configuration;

import app.console.Input;
import app.console.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Input input(Scanner systemInScanner) {
        return new Input(systemInScanner);
    }

    @Bean(destroyMethod = "")
    public Scanner systemInScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public PrintStream systemOut() {
        return System.out;
    }

    @Bean
    public Random random() {
        return new Random();
    }
}