package app.selectors;

import app.core.MazeOptions;
import app.configuration.SizeRestrictions;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class MazeSelector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final SizeRestrictions sizeRestrict;

    public MazeSelector(Terminal terminal, MazeOptions options, SizeRestrictions sizeRestrict) {
        this.terminal = terminal;
        this.options = options;
        this.sizeRestrict = sizeRestrict;
    }


    public void isNewOrOldMaze() {
        terminal.applyCurrentOptions(options.height(), options.width());
        terminal.applyCurrentOptions(options.generator());

        if (terminal.askToNeedNewMaze()) {
            options.needNewMaze(true);
        } else {
            options.needNewMaze(false);
        }
    }


    public void setSize() {
        int height = terminal.requestHeight(sizeRestrict.minSize(), sizeRestrict.maxSize());
        int width = terminal.requestWidth(sizeRestrict.minSize(), sizeRestrict.maxSize());

        options.setHeight(height);
        options.setWidth(width);
    }
}