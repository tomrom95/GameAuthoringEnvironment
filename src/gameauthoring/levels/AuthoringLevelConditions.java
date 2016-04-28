package gameauthoring.levels;

import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import gameauthoring.listdisplay.LevelConditionView;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class AuthoringLevelConditions implements Glyph{
    
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/a_level_size");
    private TransitionView myTransitions;
    private LevelConditionView myConditions; 
    private HBox myHBox;
      
    public AuthoringLevelConditions (IGame game, ILevel level) {
        myConditions = new LevelConditionView(game, level);   
        myTransitions = new TransitionView(game, level);
        init();
    }
    
    private void init () {
        myHBox = new HBox();
        myHBox.setMaxHeight(Double.parseDouble(myBundle.getString("MaxHeight")));
        myHBox.getChildren().addAll(myTransitions.draw(), myConditions.draw());
    }

    @Override
    public Node draw () {
        return myHBox;
    }

    public double getHeight () {
       return myHBox.getHeight();
    }

}
