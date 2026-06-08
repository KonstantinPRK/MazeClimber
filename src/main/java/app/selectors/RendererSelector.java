package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.RendererCatalog;
import app.algorithms.rendering.Renderer;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class RendererSelector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final RendererCatalog rendererCatalog;


    public RendererSelector(Terminal terminal, MazeOptions options, RendererCatalog rendererCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.rendererCatalog = rendererCatalog;
    }


    public void isNewOrOldRenderer() {
        terminal.applyCurrentOptions(options.renderer());

        if (terminal.askToNeedNewRenderer()) {
            options.needNewRenderer(true);
        } else {
            options.needNewRenderer(false);
        }
    }


    public void changeRenderer() {
        String rendererName = terminal.requestRendererOption(rendererCatalog.showCatalog());
        Renderer renderer = rendererCatalog.get(rendererName);
        options.setRenderer(renderer);
    }
}