package app;

import app.core.MazeRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Главный класс приложения, запускающий Spring Boot контекст и стартующий
 * выполнение основного процесса через {@link MazeRunner}.
 *
 * @author unknown
 * @version 1.0
 */
@SpringBootApplication
public class App {

    /**
     * Точка входа в приложение. Инициализирует Spring Boot контекст, получает
     * бин {@link MazeRunner} и запускает его метод {@link MazeRunner#start()}.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        MazeRunner mazeRunner = context.getBean(MazeRunner.class);
        mazeRunner.start();
    }
}