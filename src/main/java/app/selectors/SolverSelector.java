package app.selectors;

import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.SolverCatalog;
import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

/**
 * Отвечает за выбор алгоритма поиска решения (прохождения) лабиринта.
 * Использует каталог солверов {@link SolverCatalog} и взаимодействует
 * с пользователем через {@link Messenger}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class SolverSelector implements Selector {
    private final SolverCatalog solverCatalog;
    private final Messenger msg;
    private final String newSolver = "Новый способ прохождения лабиринта",
            currentSolver = "Использовать ранее выбранный алгоритм прохождения";
    private boolean isNeedNewOption = true;


    /**
     * Конструктор, инициализирующий каталог солверов и мессенджер.
     *
     * @param solverCatalog каталог доступных алгоритмов поиска пути
     * @param msg           компонент для вывода сообщений и получения ввода пользователя
     */
    public SolverSelector(SolverCatalog solverCatalog, Messenger msg) {
        this.solverCatalog = solverCatalog;
        this.msg = msg;
    }


    /**
     * Устанавливает солвер для текущей сессии.
     * Если сессия уже запускалась, предлагает выбрать новый алгоритм или
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
     * Выводит информацию о текущем солвере и запрашивает у пользователя,
     * хочет ли он выбрать новый алгоритм прохождения или оставить текущий.
     * Результат сохраняется в поле {@link #isNeedNewOption}.
     *
     * @param session текущая сессия, из которой берётся название текущего солвера
     */
    private void isNewOrOldOption(MazeSession session) {
        msg.applyCurrentOptions("Текущие параметры (алгоритм поиска решения)", ": ", session.solver().getName());
        msg.showInformation("Каким способом вы желаете найти выход из лабиринта?");
        isNeedNewOption = msg.requestRespond(newSolver, currentSolver).equals(currentSolver);
    }


    /**
     * Отображает список доступных солверов, получает выбор пользователя,
     * извлекает соответствующий алгоритм из каталога и устанавливает его в сессию.
     *
     * @param session текущая сессия, в которую будет сохранён выбранный солвер
     */
    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите алгоритм поиска решения. ");
        String solverName = msg.requestRespond(solverCatalog.showCatalog());
        Solver solver = solverCatalog.getAlgorithm(solverName);
        session.setSolver(solver);
    }
}