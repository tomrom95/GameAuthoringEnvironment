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

    public SpriteGroupDefinition getMyGroupToCheck () {
        return myGroupToCheck;
    }

    public void setMyGroupToCheck (SpriteGroupDefinition myGroupToCheck) {
        this.myGroupToCheck = myGroupToCheck;
    }

    public AttributeTypeDefinition getMyAttributeType () {
        return myAttributeType;
    }

    public void setMyAttributeType (AttributeTypeDefinition myAttributeType) {
        this.myAttributeType = myAttributeType;
    }

    public DoublePredicate getMyValueCheck () {
        return myValueCheck;
    }

    public void setMyValueCheck (DoublePredicate myValueCheck) {
        this.myValueCheck = myValueCheck;
    }

    public ListIEffectDefinition getMyApplyToSelf () {
        return myApplyToSelf;
    }

    public void setMyApplyToSelf (ListIEffectDefinition myApplyToSelf) {
        this.myApplyToSelf = myApplyToSelf;
    }

    public SpriteGroupDefinition getMyOtherGroup () {
        return myOtherGroup;
    }

    public void setMyOtherGroup (SpriteGroupDefinition myOtherGroup) {
        this.myOtherGroup = myOtherGroup;
    }

    public ListIEffectDefinition getMyApplyToOtherGroup () {
        return myApplyToOtherGroup;
    }

    public void setMyApplyToOtherGroup (ListIEffectDefinition myApplyToOtherGroup) {
        this.myApplyToOtherGroup = myApplyToOtherGroup;
    }

    public ListIEffectDefinition getMyApplyToGlobalAttys () {
        return myApplyToGlobalAttys;
    }

    public void setMyApplyToGlobalAttys (ListIEffectDefinition myApplyToGlobalAttys) {
        this.myApplyToGlobalAttys = myApplyToGlobalAttys;
    }

}
