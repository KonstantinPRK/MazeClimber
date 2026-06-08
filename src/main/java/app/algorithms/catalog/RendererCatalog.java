package app.algorithms.catalog;

import app.algorithms.rendering.Renderer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RendererCatalog {

    public List<String> showCatalog() {
        return List.of();
    }


    public Renderer get(String rendererName) {
        return null;
    }
}
