package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.ISpriteGroup;
import engine.OnClickCondition;
import engine.effects.IEffect;


public class OnClickDefinition implements IDefinition {

    private IGame myGame;
    private ISpriteGroup myGroupToCheck;
    private List<IEffect> myApplyToSelf;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnClickCondition create () {
        return new OnClickCondition(myGame, myGroupToCheck, myApplyToSelf, myApplyToOtherGroup,
                                    myOtherGroup, myApplyToGlobalAttys);
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
