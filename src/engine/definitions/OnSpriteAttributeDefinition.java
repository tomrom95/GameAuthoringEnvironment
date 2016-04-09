package engine.definitions;

import java.util.function.DoublePredicate;
import engine.OnSpriteAttributeCondition;



/**
 * 'Definition' class for the OnSpriteAttributeDefinition
 * 
 * @author jonathanim
 *
 */
public class OnSpriteAttributeDefinition implements IDefinition {

    private GameDefinition myGame;
    private SpriteGroupDefinition myGroupToCheck;

    private AttributeTypeDefinition myAttributeType;
    private DoublePredicate myValueCheck;

    private ListIEffectDefinition myApplyToSelf;
    private SpriteGroupDefinition myOtherGroup;
    private ListIEffectDefinition myApplyToOtherGroup;
    private ListIEffectDefinition myApplyToGlobalAttys;

    public OnSpriteAttributeCondition create () {
        return new OnSpriteAttributeCondition(myGame.create(), myGroupToCheck.create(),
                                              myAttributeType.create(), myValueCheck,
                                              myApplyToSelf.create(), myApplyToOtherGroup.create(),
                                              myOtherGroup.create(),
                                              myApplyToGlobalAttys.create());

    }

    public GameDefinition getMyGame () {
        return myGame;
    }

    public void setMyGame (GameDefinition myGame) {
        this.myGame = myGame;
    }

}
