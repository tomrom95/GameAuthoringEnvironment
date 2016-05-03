package gameplayer;

import engine.Drawable;
import engine.events.EventType;
import engine.events.GameEvent;


/**
 * Controls the action of the sprite display view
 * Responsible for populating the view and driving the update when necessary
 *
 * @author RyanStPierre
 *
 */
public class SpriteDisplayController {

    private ISpriteDisplay myView;
    private Drawable mySprite;

    public SpriteDisplayController (ISpriteDisplay spriteDisplay) {
        myView = spriteDisplay;
    }

    private void setAction () {
        myView.getUpgradeButton().setDisable(!mySprite.isUgradeable().get());
        mySprite.isUgradeable()
                .addListener( (a, b, newVal) -> myView.getUpgradeButton().setDisable(!newVal));
        myView.getUpgradeButton()
                .setOnMouseClicked(e -> upgrade());
    }

    private void upgrade () {
        mySprite.registerEvent(new GameEvent(EventType.UPGRADE));
        myView.clear();
    }

    public void populate (Drawable drawn) {
        mySprite = drawn;
        myView.populate(drawn);
        setAction();
    }

}
