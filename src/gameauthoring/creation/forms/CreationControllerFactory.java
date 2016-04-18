package gameauthoring.creation.forms;

import java.util.List;
import engine.Game;


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
                                                                    Game game) {
        if (title.equals("Missiles")) {
            System.out.println("missile");
            return new CreationControllerMissile(title, sfcs, game);
        }
        return new CreationControllerSprite(title, sfcs, game);
    }

    public CreationControllerAttribute createAttributeCreationController (String title,
                                                                          List<String> sfcs,
                                                                          Game game) {
        return new CreationControllerAttribute(title, sfcs, game);
    }

    public CreationControllerGlobals createGlobalsCreationController (String title, List<String> sfcs, Game game) {
        return new CreationControllerGlobals(title, sfcs, game);
    }
    public CreationControllerEvent createEventCreationController (String title,
                                                                  List<String> sfcs,
                                                                  Game game) {
        return new CreationControllerEvent(title, sfcs, game);
    }

    public CreationControllerGroup createGroupCC (String title, List<String> sfcs, Game game) {
        return new CreationControllerGroup(title, sfcs, game);

    }
}
