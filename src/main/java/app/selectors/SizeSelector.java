package app.selectors;

import app.configuration.CurrentSize;
import app.core.MazeOptions;
import app.configuration.SizeRestrictions;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class SizeSelector implements Selector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final SizeRestrictions sizeRestrict;

    public SizeSelector(Terminal terminal, MazeOptions options, SizeRestrictions sizeRestrict) {
        this.terminal = terminal;
        this.options = options;
        this.sizeRestrict = sizeRestrict;
    }

    @Override
    public void setOption(int numOfLaunches){
        if (numOfLaunches > 0) isNewOrOldOption();
        if (options.isNeedNewSize()) setSizeOption();
    }


    private void isNewOrOldOption() {
        terminal.applyCurrentOptions(options.size());

        if (terminal.askToNeedNewSize()) {
            options.needNewSize(true);
        } else {
            options.needNewSize(false);
        }
    }


    private void setSizeOption() {
        CurrentSize size = terminal.requestSize(sizeRestrict.minSize(), sizeRestrict.maxSize());
        options.setNewSize(size);
    }
}