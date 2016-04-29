package gameauthoring.creation.factories;

import engine.IGame;
import gameauthoring.tabs.ITabViewer;

/**
 * Creating a factory to generate the views required for the 
 * authoring environment
 * 
 * @author Dhrumil
 *
 */
public class TabViewFactory<T extends ITabViewer> {

    private IGame myGame;

    public TabViewFactory (IGame game) {
        myGame = game;
    }
    
    
}
