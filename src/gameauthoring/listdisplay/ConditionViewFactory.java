package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;


public class ConditionViewFactory {

    private static final String path = "defaults/condition_view_fact";
    private ResourceBundle myBundle = ResourceBundle.getBundle(path);
    
    private IGame myGame;
    private ILevel myLevel;

    public ConditionViewFactory (IGame game) {
        myGame = game;
    }

    public ConditionViewFactory (IGame game, ILevel level) {
        this(game);
        myLevel = level;
    }

    public SubConditionView get (String selection) throws ClassNotFoundException,
                                                   InstantiationException, IllegalAccessException,
                                                   IllegalArgumentException,
                                                   InvocationTargetException, SecurityException {
        selection.trim();
        return getSubView(selection);
    }

    private SubConditionView getSubView (String key) throws ClassNotFoundException,
                                                     InstantiationException, IllegalAccessException,
                                                     IllegalArgumentException,
                                                     InvocationTargetException, SecurityException {     
        String name = (myBundle.getString(key));
        if (name.split(",").length == 1) {
            Class<?> c = Class.forName(name);
            return (SubConditionView) c.getConstructors()[0].newInstance(myGame);
        }
        else {
            Class<?> c = Class.forName(name.split(",")[0]);
            return (SubConditionView) c.getConstructors()[0].newInstance(myGame, myLevel);
        }

    }
}
