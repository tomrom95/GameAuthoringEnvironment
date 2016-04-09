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

}
