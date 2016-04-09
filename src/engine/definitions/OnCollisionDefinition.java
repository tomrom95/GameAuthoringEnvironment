package engine.definitions;


import engine.OnCollisionCondition;


/**
 * 'Definition' class for the OnCollisionCondition
 * 
 * @author jonathanim
 *
 */
public class OnCollisionDefinition implements IDefinition {

    private GameDefinition myGame;
    private SpriteGroupDefinition myGroupA;
    private SpriteGroupDefinition myGroupB;
    private ListIEffectDefinition myApplyToA;
    private ListIEffectDefinition myApplyToB;
    private ListIEffectDefinition myApplyToOtherGroup;
    private SpriteGroupDefinition myOtherGroup;
    private ListIEffectDefinition myApplyToGlobalAttys;

    public OnCollisionCondition create () {
        return new OnCollisionCondition(myGame.create(), myGroupA.create(), myGroupB.create(),
                                        myApplyToA.create(), myApplyToB.create(),
                                        myApplyToOtherGroup.create(), myOtherGroup.create(),
                                        myApplyToGlobalAttys.create());
    }

    public GameDefinition getMyGame () {
        return myGame;
    }

    public void setMyGame (GameDefinition myGame) {
        this.myGame = myGame;
    }

}
