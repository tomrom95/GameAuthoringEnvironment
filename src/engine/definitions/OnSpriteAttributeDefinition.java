package engine.definitions;

import java.util.List;
import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IGame;
import engine.ISpriteGroup;
import engine.OnSpriteAttributeCondition;
import engine.effects.IEffect;


public class OnSpriteAttributeDefinition implements IDefinition {

    private IGame myGame;
    private ISpriteGroup myGroupToCheck;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;
    private List<IEffect> myApplyToSelf;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnSpriteAttributeCondition create () {
        return new OnSpriteAttributeCondition(myGame, myGroupToCheck, myAttributeType, myValueCheck,
                                              myApplyToSelf, myApplyToOtherGroup, myOtherGroup,
                                              myApplyToGlobalAttys);

    }

    public IGame getMyGame () {
        return myGame;
    }

    public void setMyGame (IGame myGame) {
        this.myGame = myGame;
    }

    public ISpriteGroup getMyGroupToCheck () {
        return myGroupToCheck;
    }

    public void setMyGroupToCheck (ISpriteGroup myGroupToCheck) {
        this.myGroupToCheck = myGroupToCheck;
    }

    public AttributeType getMyAttributeType () {
        return myAttributeType;
    }

    public void setMyAttributeType (AttributeType myAttributeType) {
        this.myAttributeType = myAttributeType;
    }

    public DoublePredicate getMyValueCheck () {
        return myValueCheck;
    }

    public void setMyValueCheck (DoublePredicate myValueCheck) {
        this.myValueCheck = myValueCheck;
    }

    public List<IEffect> getMyApplyToSelf () {
        return myApplyToSelf;
    }

    public void setMyApplyToSelf (List<IEffect> myApplyToSelf) {
        this.myApplyToSelf = myApplyToSelf;
    }

    public ISpriteGroup getMyOtherGroup () {
        return myOtherGroup;
    }

    public void setMyOtherGroup (ISpriteGroup myOtherGroup) {
        this.myOtherGroup = myOtherGroup;
    }

    public List<IEffect> getMyApplyToOtherGroup () {
        return myApplyToOtherGroup;
    }

    public void setMyApplyToOtherGroup (List<IEffect> myApplyToOtherGroup) {
        this.myApplyToOtherGroup = myApplyToOtherGroup;
    }

    public List<IEffect> getMyApplyToGlobalAttys () {
        return myApplyToGlobalAttys;
    }

    public void setMyApplyToGlobalAttys (List<IEffect> myApplyToGlobalAttys) {
        this.myApplyToGlobalAttys = myApplyToGlobalAttys;
    }
}
