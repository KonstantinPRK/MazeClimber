package app.selectors;

import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.GeneratorCatalog;
import app.algorithms.generation.Generator;
import org.springframework.stereotype.Component;

/**
 * Отвечает за выбор алгоритма генерации лабиринта.
 * Использует каталог генераторов {@link GeneratorCatalog} и взаимодействует
 * с пользователем через {@link Messenger}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class GeneratorSelector implements Selector {
    private final GeneratorCatalog generatorCatalog;
    private final Messenger msg;
    private final String newGenerator = "Выбрать новый генератор",
            currentGenerator = "Использовать текущий генератор";
    private boolean isNeedNewOption = true;


    /**
     * Конструктор, инициализирующий зависимости и ссылку на мессенджер.
     *
     * @param generatorCatalog каталог доступных алгоритмов генерации
     * @param messenger        компонент для вывода сообщений и получения ввода пользователя
     */
    public GeneratorSelector(GeneratorCatalog generatorCatalog, Messenger messenger) {
        this.generatorCatalog = generatorCatalog;
        this.msg = messenger;
    }


    /**
     * Устанавливает генератор для текущей сессии.
     * Если сессия уже запускалась, предлагает выбрать новый генератор или
     * оставить текущий. При необходимости вызывает интерактивный выбор.
     *
     * @param session текущий объект сессии лабиринта
     */
    @Override
    public void setOption(MazeSession session) {
        if (session.numOfLaunches() > 0) isNewOrOldOption(session);
        if (isNeedNewOption) selectOption(session);
    }


    /**
     * Выводит информацию о текущем генераторе и запрашивает у пользователя,
     * хочет ли он выбрать новый генератор или оставить текущий.
     * Результат сохраняется в поле {@link #isNeedNewOption}.
     *
     * @param session текущая сессия, из которой берётся название текущего генератора
     */
    private void isNewOrOldOption(MazeSession session) {
        msg.applyCurrentOptions("Текущие параметры (алгоритм генерации)", ": ", session.generator().getName());
        msg.showInformation("Каким способом будет сгенерирован новый лабиринт ?");
        isNeedNewOption = msg.requestRespond(newGenerator, currentGenerator).equals(currentGenerator);
    }


    /**
     * Отображает список доступных генераторов, получает выбор пользователя,
     * извлекает соответствующий алгоритм из каталога и устанавливает его в сессию.
     *
     * @param session текущая сессия, в которую будет сохранён выбранный генератор
     */
    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите алгоритм генерации лабиринта. ");
        String generatorName = msg.requestRespond(generatorCatalog.showCatalog());
        Generator generator = generatorCatalog.getAlgorithm(generatorName);
        session.setGenerator(generator);
    }
}