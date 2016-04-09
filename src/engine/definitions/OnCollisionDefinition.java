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

    public SpriteGroupDefinition getMyGroupA () {
        return myGroupA;
    }

    public void setMyGroupA (SpriteGroupDefinition myGroupA) {
        this.myGroupA = myGroupA;
    }

    public SpriteGroupDefinition getMyGroupB () {
        return myGroupB;
    }

    public void setMyGroupB (SpriteGroupDefinition myGroupB) {
        this.myGroupB = myGroupB;
    }

    public ListIEffectDefinition getMyApplyToA () {
        return myApplyToA;
    }

    public void setMyApplyToA (ListIEffectDefinition myApplyToA) {
        this.myApplyToA = myApplyToA;
    }

    public ListIEffectDefinition getMyApplyToB () {
        return myApplyToB;
    }

    public void setMyApplyToB (ListIEffectDefinition myApplyToB) {
        this.myApplyToB = myApplyToB;
    }

    public ListIEffectDefinition getMyApplyToOtherGroup () {
        return myApplyToOtherGroup;
    }

    public void setMyApplyToOtherGroup (ListIEffectDefinition myApplyToOtherGroup) {
        this.myApplyToOtherGroup = myApplyToOtherGroup;
    }

    public SpriteGroupDefinition getMyOtherGroup () {
        return myOtherGroup;
    }

    public void setMyOtherGroup (SpriteGroupDefinition myOtherGroup) {
        this.myOtherGroup = myOtherGroup;
    }

    public ListIEffectDefinition getMyApplyToGlobalAttys () {
        return myApplyToGlobalAttys;
    }

    public void setMyApplyToGlobalAttys (ListIEffectDefinition myApplyToGlobalAttys) {
        this.myApplyToGlobalAttys = myApplyToGlobalAttys;
    }


}
