package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;


public class ConditionViewFactory {

    private static final String PATH = "defaults/condition_view_fact";
    private static final String DIVIDER = ",";
    private static final int FIRST_INDEX = 0;
    private static final int ONE_ITEM = 1;
    
    private ResourceBundle myBundle = ResourceBundle.getBundle(PATH);
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
        if (name.split(DIVIDER).length == ONE_ITEM) {
            Class<?> c = Class.forName(name);
            return (SubConditionView) c.getConstructors()[0].newInstance(myGame);
        }
        else {
            Class<?> c = Class.forName(getFirst(name));
            return (SubConditionView) c.getConstructors()[0].newInstance(myGame, myLevel);
        }

    }

    private String getFirst (String name) {
        return name.split(DIVIDER)[FIRST_INDEX];
    }
}
