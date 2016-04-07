package engine;

import java.util.List;
import java.util.function.DoublePredicate;
import effects.IEffect;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import util.Coordinate;
import util.TimeDuration;


public class OnClickCondition implements ICondition {

    private IGame myGame;
    private ISpriteGroup myGroupToCheck;
    private List<IEffect> myApplyToSelf;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnClickCondition (IGame game,
                             ISpriteGroup groupToCheck,
                             List<IEffect> applyToSelf,
                             List<IEffect> applyToOtherGroup,
                             ISpriteGroup otherGroup,
                             List<IEffect> applyToGlobalAttys) {
        myGame = game;
        myGroupToCheck = groupToCheck;
        myApplyToSelf = applyToSelf;
        myOtherGroup = otherGroup;
        myApplyToOtherGroup = applyToOtherGroup;
        myApplyToGlobalAttys = applyToGlobalAttys;
    }

    @Override
    public void update (TimeDuration duration) {
        // do nothing
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        Coordinate coord = new Coordinate(mouseEvent.getX(), mouseEvent.getY());
        myGame.getLevelManager().getCurrentLevel().get().getSprites().stream()
                .filter(sprite -> sprite.get().getType().equals(myGroupToCheck))
                .filter(sprite -> sprite.get().getBounds().contains(coord))
                .forEach(sprite -> handleAction(sprite.get()));
    }

    private void handleAction (ISprite sprite) {
        myApplyToSelf.forEach(effect -> sprite.applyEffect(effect));
        myApplyToOtherGroup
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel().get().getSprites()
                        .stream()
                        .filter(otherSprite -> otherSprite.get().getType().equals(myGroupToCheck))
                        .forEach(otherSprite -> otherSprite.get().applyEffect(effect)));
        myApplyToGlobalAttys
                .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
        myApplyToGlobalAttys.forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
                .getAttributeManager().get().applyEffect(effect));
    }

}
