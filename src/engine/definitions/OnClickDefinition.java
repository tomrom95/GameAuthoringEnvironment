package engine.definitions;

import engine.OnClickCondition;


/**
 * 'Definition' class for the OnClickCondition
 *
 * @author jonathanim
 *
 */
public class OnClickDefinition implements IDefinition {

    private GameDefinition myGame;
    private SpriteGroupDefinition myGroupToCheck;
    private ListIEffectDefinition myApplyToSelf;
    private SpriteGroupDefinition myOtherGroup;
    private ListIEffectDefinition myApplyToOtherGroup;
    private ListIEffectDefinition myApplyToGlobalAttys;

    public OnClickCondition create () {
        return new OnClickCondition(myGame.create(), myGroupToCheck.create(),
                                    myApplyToSelf.create(),
                                    myApplyToOtherGroup.create(),
                                    myOtherGroup.create(), myApplyToGlobalAttys.create());
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
