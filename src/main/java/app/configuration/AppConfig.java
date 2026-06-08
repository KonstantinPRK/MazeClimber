package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.PrintStream;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean(destroyMethod = "")
    public Scanner systemInScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public PrintStream systemOut() {
        return System.out;
    }
}