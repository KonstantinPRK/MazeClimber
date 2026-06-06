package App.selectors;
import App.configuration.*;

public class MazeSelector extends Selector {
    private SizeRestrictions sizeRestrict;

    public MazeSelector(SizeRestrictions sizeRestrictions){
        this.sizeRestrict = sizeRestrictions;
    }


    protected void isNewOrOldMaze() {
        terminal.applyCurrentOptions(mazeOptions.height(), mazeOptions.width());
        terminal.applyCurrentOptions(mazeOptions.generator());

        if(terminal.askToNeedNewMaze()){
            mazeOptions.needNewMaze(true);
        } else {
            mazeOptions.needNewMaze(false);
        }
    }


    protected void setSize(){
        int height = terminal.requestHeight(sizeRestrict.minSize(), sizeRestrict.maxSize());
        int width = terminal.requestWidth(sizeRestrict.minSize(), sizeRestrict.maxSize());

        mazeOptions.setHeight(height);
        mazeOptions.setWidth(width);
    }
}
