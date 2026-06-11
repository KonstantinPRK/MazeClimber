package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.RendererCatalog;
import app.algorithms.rendering.Renderer;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class RendererSelector implements Selector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final RendererCatalog rendererCatalog;


    public RendererSelector(Terminal terminal, MazeOptions options, RendererCatalog rendererCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.rendererCatalog = rendererCatalog;
    }

    @Override
    public void setOption(int numOfLaunches) {
        if (numOfLaunches > 0) isNewOrOldOption();
        if (options.isNeedNewRenderer()) setRendererOption();
    }


    private void isNewOrOldOption() {
        terminal.applyCurrentOptions(options.renderer());

        if (terminal.askToNeedNewRenderer()) {
            options.needNewRenderer(true);
        } else {
            options.needNewRenderer(false);
        }
    }


    private void setRendererOption() {
        String rendererName = terminal.requestRendererOption(rendererCatalog.showCatalog());
        Renderer renderer = rendererCatalog.getAlgorithm(rendererName);
        options.setRenderer(renderer);
    }
}