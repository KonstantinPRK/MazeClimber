package App.selectors;
import algorithms.catalog.*;
import algorithms.rendering.*;
import maze.*;

import java.util.List;

public class RendererSelector extends Selector {
    private RendererCatalog rendererCatalog;

    public RendererSelector(RendererCatalog rendererCatalog) {
        this.rendererCatalog = rendererCatalog;
    }


    protected void isNewOrOldRenderer() {
        terminal.applyCurrentOptions(mazeOptions.renderer());

        if(terminal.askToNeedNewRenderer()){
            mazeOptions.needNewRenderer(true);
        } else {
            mazeOptions.needNewRenderer(false);
        }
    }


    protected void changeRenderer() {
        String rendererName = terminal.requestRendererOption(rendererCatalog.showCatalog());
        Renderer renderer = rendererCatalog.get(rendererName);
        mazeOptions.setRenderer(renderer);
    }


    protected void toRenderMazeSolution() {
        Renderer renderer = mazeOptions.renderer();
        renderer.render(maze, path);
    }
}
