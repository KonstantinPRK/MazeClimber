package app.selectors;

import app.configuration.CurrentSize;
import app.console.Messenger;
import app.core.MazeSession;
import app.configuration.SizeRestrictions;
import org.springframework.stereotype.Component;

@Component
public class SizeSelector implements Selector {
    private final SizeRestrictions sizeRestrictions;
    private final Messenger msg;
    private final String newSize = "Изменить размерность",
                         currentSize = "Использовать текущую размерность";
    private boolean isNeedNewOption = true;


    public SizeSelector(SizeRestrictions sizeRestrictions, Messenger msg) {
        this.sizeRestrictions = sizeRestrictions;
        this.msg = msg;
    }


    @Override
    public void setOption(MazeSession session){
        if(session.numOfLaunches() > 0) isNewOrOldOption(session);
        if(isNeedNewOption) selectOption(session);

    }


    private void isNewOrOldOption(MazeSession session){
        msg.showInformation("Текущие параметры (размер лабиринта): ");
        msg.applyCurrentOptions("Высота", ": ", String.valueOf(session.size().height()));
        msg.applyCurrentOptions("Ширина", ": ", String.valueOf(session.size().width()));

        msg.showInformation("Какой размерности должен быть новый лабиринт ?");
        isNeedNewOption = msg.requestRespond(newSize, currentSize) == newSize;
    }


    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите высоту лабиринта. ");
        int height = msg.requestRespond(sizeRestrictions.minSize(), sizeRestrictions.maxSize());

        msg.showInformation("Выберите ширину лабиринта. ");
        int width = msg.requestRespond(sizeRestrictions.minSize(), sizeRestrictions.maxSize());

        session.setSize(new CurrentSize(height, width));
    }
}