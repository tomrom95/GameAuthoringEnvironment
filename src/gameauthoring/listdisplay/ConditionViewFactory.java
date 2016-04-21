package gameauthoring.listdisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import engine.IGame;
import engine.ILevel;


public class ConditionViewFactory {

    private IGame myGame;
    private ILevel myLevel;

    public ConditionViewFactory (IGame game) {
        myGame = game;
    }

    public ConditionViewFactory (IGame game, ILevel level) {
        this(game);
        myLevel = level;
    }

    public SubConditionView get (String selection) {
        selection.trim();
        // TODO replace with reflection
        switch (selection) {
            case "OnClickCondition":
                return test(myGame, "OnClickCondition");
            case "OnCollisionCondition":
                return new OnCollisionView(myGame);
            case "OnGlobalAttribute":
                return new OnGlobalView(myGame);
            case "OnSpriteAttribute":
                return new OnSpriteView(myGame);
            case "Global Attribute End Condition":
                return new GlobalEndView(myGame, myLevel);
            case "Sprite Attribute End Condition":
                return new SpriteEndView(myGame, myLevel);
            case "On Click End Condition":
                return new ClickEndView(myGame, myLevel);
        }

        return null;
    }

    private SubConditionView test (IGame game, String key) {
       String path = "defaults/condition_view_fact";
       ResourceBundle bundle = ResourceBundle.getBundle(path);
       String name = (bundle.getString(key));
       try {
        Class<?> c = Class.forName(name);
        return (SubConditionView) c.getConstructors()[0].newInstance(game);
    }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       
       
       
       return new OnCollisionView(game);
    }
}
