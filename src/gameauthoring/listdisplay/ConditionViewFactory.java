package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;
import gameauthoring.creation.factories.ReflectionException;


public class ConditionViewFactory implements ISubViewFactory {

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

    @Override
    public SubConditionView interpret (String selection) throws ReflectionException {
        selection.trim();
        return getSubView(selection);
    }

    private SubConditionView getSubView (String key) throws ReflectionException {
        String name = myBundle.getString(key);
        try {
            if (name.split(DIVIDER).length == ONE_ITEM) {
                Class<?> c = Class.forName(name);
                return (SubConditionView) c.getConstructors()[FIRST_INDEX].newInstance(myGame);
            }
            else {
                Class<?> c = Class.forName(getFirst(name));
                return (SubConditionView) c.getConstructors()[FIRST_INDEX].newInstance(myGame,
                                                                                       myLevel);
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            throw new ReflectionException(e.getMessage());
        }

    }

    private String getFirst (String name) {
        return name.split(DIVIDER)[FIRST_INDEX];
    }
}
