package app.selectors;

import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.RendererCatalog;
import app.algorithms.rendering.Renderer;
import org.springframework.stereotype.Component;

/**
 * Отвечает за выбор алгоритма отрисовки лабиринта.
 * Использует каталог рендереров {@link RendererCatalog} и взаимодействует
 * с пользователем через {@link Messenger}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class RendererSelector implements Selector {
    private final RendererCatalog rendererCatalog;
    private final Messenger msg;
    private final String newRenderer = "Новый способ зарисовки лабиринта",
            currentRenderer = "Использовать ранее выбранный алгоритм зарисовки";
    private boolean isNeedNewOption = true;


    /**
     * Конструктор, инициализирующий каталог рендереров и мессенджер.
     *
     * @param rendererCatalog каталог доступных алгоритмов отрисовки
     * @param msg             компонент для вывода сообщений и получения ввода пользователя
     */
    public RendererSelector(RendererCatalog rendererCatalog, Messenger msg) {
        this.rendererCatalog = rendererCatalog;
        this.msg = msg;
    }


    /**
     * Устанавливает рендерер для текущей сессии.
     * Если сессия уже запускалась, предлагает выбрать новый рендерер или
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
     * Выводит информацию о текущем рендерере и запрашивает у пользователя,
     * хочет ли он выбрать новый способ отрисовки или оставить текущий.
     * Результат сохраняется в поле {@link #isNeedNewOption}.
     *
     * @param session текущая сессия, из которой берётся название текущего рендерера
     */
    private void isNewOrOldOption(MazeSession session) {
        msg.applyCurrentOptions("Текущие параметры (алгоритм отображения лабиринта)", ": ", session.renderer().getName());
        msg.showInformation("Как вы желаете продолжить отображение лабиринта?");
        isNeedNewOption = msg.requestRespond(newRenderer, currentRenderer).equals(newRenderer);
    }


    /**
     * Отображает список доступных рендереров, получает выбор пользователя,
     * извлекает соответствующий алгоритм из каталога и устанавливает его в сессию.
     *
     * @param session текущая сессия, в которую будет сохранён выбранный рендерер
     */
    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите алгоритм отрисовки лабиринта. ");
        String rendererName = msg.requestRespond(rendererCatalog.showCatalog());
        Renderer renderer = rendererCatalog.getAlgorithm(rendererName);
        session.setRenderer(renderer);
    }
}