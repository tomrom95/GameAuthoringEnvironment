package usecases;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import engine.IScreenEventFactory;
import engine.IGlobalEffect;
import javafx.scene.layout.Pane;


/**
 * Demonstrates how clicks and key inputs are watched, converted into effects that our game engine
 * can interpret, and
 * pass throughout the engine as necessary
 * 
 * It is the job of this screen interaction class to watch and change these clicks and key inputs it
 * IGlobalEffects. Then this class
 * simple queues these effects. Every time the game loop in a given game occurs there is a step that
 * dequeues these effects and
 * passes them to the current level to process. The effects are queued and retrieved all at once for
 * two reasons. First, it
 * gives us great flexibility when these effects are processed. Second, it avoids concurrency
 * errors. It is quite possible that
 * if these effects propagated immediately they could influence the game mid-update in an
 * undesirable way (i.e cause an effect
 * that will cause a Sprite to add to a list that is currently being looped over).
 * 
 * The Interaction interpreter is given a pane on which it watches. This is the main pane where the
 * game screen is rendered.
 * This is instantiated once for the game and passed by reference to this class.
 * 
 * @author RyanStPierre
 *
 */

public class ScreenInteractionUseCase {

    /* Factory used to convert JavaFX ActionEvents to their corresponding IGlobalEffect */
    private IScreenEventFactory factoryMock;
    /* All IGlobalEffects stored after last dequeue */
    private List<IGlobalEffect> myQueue;

    public ScreenInteractionUseCase (Pane pane) {
        myQueue = new ArrayList<IGlobalEffect>();
        factoryMock = EasyMock.createMock(IScreenEventFactory.class);
        setUpWatchers(pane);
    }

    /**
     * Set ups the watchers the wait for clicks/key input to interpret
     * This method relies on the IScreenEventFactory. The IScreenEvent factory takes in a JavaFX
     * ActionEvent and returns
     * the proper IGlobalEffect to pass to the game
     */
    private void setUpWatchers (Pane pane) {
        pane.setOnMouseClicked(e -> myQueue.add(factoryMock.interpretEvent(e)));
        pane.setOnKeyPressed(e -> myQueue.add(factoryMock.interpretEvent(e)));
    }

    /**
     * Gives access to the queued effects that the class has interpreted
     * 
     * @return list of queued effects
     */

    public List<IGlobalEffect> deQueueEffects () {

        List<IGlobalEffect> copy = new ArrayList<IGlobalEffect>(myQueue);
        myQueue.clear();
        return copy;
    }
}
