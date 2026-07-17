package app.selectors;

import app.algorithms.generation.Generator;
import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.RendererCatalog;
import app.algorithms.rendering.Renderer;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class RendererSelector implements Selector {
    private final RendererCatalog rendererCatalog;
    private final Messenger msg;
    private final String newRenderer = "Новый способ зарисовки лабиринта",
            currentRenderer = "Использовать ранее выбранный алгоритм зарисовки";
    private boolean isNeedNewOption = true;

    public RendererSelector(RendererCatalog rendererCatalog, Messenger msg) {
        this.rendererCatalog = rendererCatalog;
        this.msg = msg;
    }


    @Override
    public void setOption(MazeSession session) {
        if(session.numOfLaunches() > 0) isNewOrOldOption(session);
        if(isNeedNewOption) selectOption(session);
    }


    private void isNewOrOldOption(MazeSession session) {
        msg.applyCurrentOptions("Текущие параметры (алгоритм отображения лабиринта)", ": ", session.renderer().getName());
        msg.showInformation("Как вы желаете продолжить отображение лабиринта?");
        isNeedNewOption = msg.requestRespond(newRenderer, currentRenderer).equals(newRenderer);
    }


    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите алгоритм отрисовки лабиринта. ");
        String rendererName = msg.requestRespond(rendererCatalog.showCatalog());
        Renderer renderer = rendererCatalog.getAlgorithm(rendererName);
        session.setRenderer(renderer);
    }
}