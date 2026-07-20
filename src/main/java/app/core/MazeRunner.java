package app.core;

import app.selectors.Selector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Основной управляющий компонент приложения, который координирует процесс
 * создания, решения и отображения лабиринтов. Использует набор селекторов
 * для настройки параметров и {@link Climber} для выполнения основных операций.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class MazeRunner {
    private final SessionManager sessionManager;
    private final Selector sizeSelector, generatorSelector, solverSelector, rendererSelector;
    private final Climber climber;


    /**
     * Конструктор, инициализирующий все необходимые зависимости.
     * Селекторы помечены {@link Qualifier} для правильного внедрения
     * конкретных реализаций.
     *
     * @param sizeSelector       селектор для выбора размера лабиринта
     * @param generatorSelector  селектор для выбора алгоритма генерации
     * @param solverSelector     селектор для выбора алгоритма поиска пути
     * @param rendererSelector   селектор для выбора алгоритма отрисовки
     * @param climber            компонент, выполняющий основные действия с лабиринтом
     * @param sessionManager     менеджер для создания и управления сессиями
     */
    public MazeRunner(@Qualifier("sizeSelector") Selector sizeSelector,
                      @Qualifier("generatorSelector") Selector generatorSelector,
                      @Qualifier("solverSelector") Selector solverSelector,
                      @Qualifier("rendererSelector") Selector rendererSelector,
                      Climber climber,
                      SessionManager sessionManager) {
        this.sizeSelector = sizeSelector;
        this.generatorSelector = generatorSelector;
        this.solverSelector = solverSelector;
        this.rendererSelector = rendererSelector;
        this.climber = climber;
        this.sessionManager = sessionManager;
    }


    /**
     * Запускает основной процесс: приветствие пользователя, создание сессии
     * и последовательное выполнение цикла "выбор параметров → работа с лабиринтом".
     */
    public void start() {
        climber.sayHello();
        MazeSession session = sessionManager.createSession();
        mazeClimbing(session);
    }


    /**
     * Цикл работы с лабиринтом. Повторяется до тех пор, пока пользователь
     * не решит завершить программу.
     *
     * @param session текущая сессия лабиринта
     */
    private void mazeClimbing(MazeSession session) {
        do {
            selection(session);
            climbing(session);
        } while (climber.isContinue(session));
    }


    /**
     * Последовательно вызывает все селекторы для настройки параметров сессии:
     * размер, генератор, солвер и рендерер.
     *
     * @param session текущая сессия, в которую сохраняются выбранные параметры
     */
    private void selection(MazeSession session) {
        sizeSelector.setOption(session);
        generatorSelector.setOption(session);
        solverSelector.setOption(session);
        rendererSelector.setOption(session);
    }


    /**
     * Выполняет основные операции с лабиринтом: создание, поиск пути и отрисовку
     * результата через вызовы соответствующих методов {@link Climber}.
     *
     * @param session текущая сессия с настроенными параметрами
     */
    private void climbing(MazeSession session) {
        climber.createNewMaze(session);
        climber.solveMaze(session);
        climber.toRenderMazeSolution(session);
    }
}