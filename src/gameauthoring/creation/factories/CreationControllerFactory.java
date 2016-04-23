package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.CreationControllerAttribute;
import gameauthoring.creation.forms.CreationControllerEvent;
import gameauthoring.creation.forms.CreationControllerGlobals;
import gameauthoring.creation.forms.CreationControllerGroup;
import gameauthoring.creation.forms.CreationControllerMissile;
import gameauthoring.creation.forms.CreationControllerSprite;


/**
 * This is a factory class for the creation of Creation Controllers
 * 
 * @author Jeremy Schreck
 *
 */
public class CreationControllerFactory {
    
    
    public CreationControllerFactory () {
    }

    public CreationController<?> createCreationController(String className, String title, List<String> sfcs, IGame game){
        try {
            return (CreationController<?>) Reflection.createInstance(className, title, sfcs, game);
        } catch (ReflectionException e){
            System.out.println("reflection exception" + e.getMessage());
            //TODO handle exception
        } catch (ClassCastException e) {
            //TODO handle exception
            System.out.println("class cast exception" + e.getMessage());

        }
        System.out.println(className);
        return null;
    }
    public CreationController<?> createCreationController(String className){
        try {
            return (CreationController<?>) Reflection.createInstance(className, new ArrayList<>());
        } catch (ReflectionException e){
            System.out.println("reflection exception" + e.getMessage());
            //TODO handle exception
        } catch (ClassCastException e) {
            //TODO handle exception
            System.out.println("class cast exception" + e.getMessage());

        }
        System.out.println(className);
        return null;
    }
    public CreationControllerSprite createSpriteCreationController (String title,
                                                                    List<String> sfcs,
                                                                    IGame myGame) {
        if (title.equals("Missiles")) {
            System.out.println("missile");
            return new CreationControllerMissile(title, sfcs, myGame);
        }
        return new CreationControllerSprite(title, sfcs, myGame);
    }

    public CreationControllerAttribute createAttributeCreationController (String title,
                                                                          List<String> sfcs,
                                                                          IGame myGame) {
        return new CreationControllerAttribute(title, sfcs, myGame);
    }

    public CreationControllerGlobals createGlobalsCreationController (String title,
                                                                      List<String> sfcs,
                                                                      IGame myGame) {
        return new CreationControllerGlobals(title, sfcs, myGame);
    }

    public CreationControllerEvent createEventCreationController (String title,
                                                                  List<String> sfcs,
                                                                  IGame myGame) {
        return new CreationControllerEvent(title, sfcs, myGame);
    }

    public CreationControllerGroup createGroupCC (String title, List<String> sfcs, IGame myGame) {
        return new CreationControllerGroup(title, sfcs, myGame);

    }
}
