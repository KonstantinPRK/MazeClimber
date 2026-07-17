package app.selectors;

import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.GeneratorCatalog;
import app.algorithms.generation.Generator;
import org.springframework.stereotype.Component;

@Component
public class GeneratorSelector implements Selector {
    private final GeneratorCatalog generatorCatalog;
    private final Messenger msg;
    private final String newGenerator = "Выбрать новый генератор",
                         currentGenerator = "Использовать текущий генератор";
    private boolean isNeedNewOption = true;


    public GeneratorSelector(GeneratorCatalog generatorCatalog, Messenger messenger) {
        this.generatorCatalog = generatorCatalog;
        this.msg = messenger;
    }


    @Override
    public void setOption(MazeSession session){
        if(session.numOfLaunches() > 0) isNewOrOldOption(session);
        if(isNeedNewOption) selectOption(session);
    }


    private void isNewOrOldOption(MazeSession session) {
        msg.applyCurrentOptions("Текущие параметры (алгоритм генерации)", ": ", session.generator().getName());
        msg.showInformation("Каким способом будет сгенерирован новый лабиринт ?");
        isNeedNewOption = msg.requestRespond(newGenerator, currentGenerator).equals(currentGenerator);
    }


    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите алгоритм генерации лабиринта. ");
        String generatorName = msg.requestRespond(generatorCatalog.showCatalog());
        Generator generator = generatorCatalog.getAlgorithm(generatorName);
        session.setGenerator(generator);
    }
}