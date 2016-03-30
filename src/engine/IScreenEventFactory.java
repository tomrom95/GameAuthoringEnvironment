package engine;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Takes a JavaFX ActionEvent from the screen and returns an effect that can be handled by the game engine
 *
 */
public interface IScreenEventFactory {

    IEffect interpreterEvent(MouseEvent event); 

    IEffect interpreterEvent (KeyEvent event); 
    
}
