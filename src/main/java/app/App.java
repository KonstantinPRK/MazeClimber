package app;

import app.core.MazeRunner;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        MazeRunner mazeRunner = context.getBean(MazeRunner.class);
        mazeRunner.start();
    }
}