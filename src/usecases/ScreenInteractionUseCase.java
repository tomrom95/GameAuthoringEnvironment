package usecases;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import engine.IScreenEventFactory;
import engine.IEffect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Demonstrates how clicks and key inputs are watched, converted into effects that our game engine can interpret, and 
 * propogated throughout the engine
 * @author RyanStPierre
 *
 */

public class ScreenInteractionUseCase {

    private IScreenEventFactory factoryMock;
    private List<IEffect> myQueue;

    public ScreenInteractionUseCase (Pane pane) {
        myQueue = new ArrayList<IEffect>();
        factoryMock = EasyMock.createMock(IScreenEventFactory.class);
        pane.setOnMouseClicked(e -> interpretClick(e));
    }

    private void interpretClick (MouseEvent e) {
        myQueue.add(factoryMock.interpretEvent(e));
    }

    public List<IEffect> deQueueEffects () {

        List<IEffect> copy = new ArrayList<IEffect>(myQueue);
        myQueue.clear();
        return copy;
    }
}
