package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import gameauthoring.listdisplay.LevelConditionView;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class AuthoringLevelConditions implements Glyph{
    
    private TransitionView myTransitions;
    private LevelConditionView myConditions; 
      
    public AuthoringLevelConditions (IGame game, ILevel level) {
        myConditions = new LevelConditionView(game, level);   
        myTransitions = new TransitionView(game, level);
    }
    
    @Override
    public Node draw () {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(myTransitions.draw(), myConditions.draw());
        return hbox;
    }

}
