package gameauthoring.creation.factories;

import java.util.List;
import engine.IGame;
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
