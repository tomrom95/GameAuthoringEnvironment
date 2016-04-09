package engine.definitions;

import java.util.List;
import engine.IGame;
import engine.ISpriteGroup;
import engine.OnCollisionCondition;
import engine.effects.IEffect;


public class OnCollisionDefinition implements IDefinition {
    
    private IGame myGame;
    private ISpriteGroup myGroupA;
    private ISpriteGroup myGroupB;
    private List<IEffect> myApplyToA;
    private List<IEffect> myApplyToB;
    private List<IEffect> myApplyToOtherGroup;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnCollisionCondition create () {
        return new OnCollisionCondition(myGame, myGroupA, myGroupB, myApplyToA, myApplyToB,
                                        myApplyToOtherGroup, myOtherGroup, myApplyToGlobalAttys);
    }
    
    public IGame getMyGame () {
        return myGame;
    }

    public void setMyGame (IGame myGame) {
        this.myGame = myGame;
    }

    public ISpriteGroup getMyGroupA () {
        return myGroupA;
    }

    public void setMyGroupA (ISpriteGroup myGroupA) {
        this.myGroupA = myGroupA;
    }

    public ISpriteGroup getMyGroupB () {
        return myGroupB;
    }

    public void setMyGroupB (ISpriteGroup myGroupB) {
        this.myGroupB = myGroupB;
    }

    public List<IEffect> getMyApplyToA () {
        return myApplyToA;
    }

    public void setMyApplyToA (List<IEffect> myApplyToA) {
        this.myApplyToA = myApplyToA;
    }

    public List<IEffect> getMyApplyToB () {
        return myApplyToB;
    }

    public void setMyApplyToB (List<IEffect> myApplyToB) {
        this.myApplyToB = myApplyToB;
    }

    public List<IEffect> getMyApplyToOtherGroup () {
        return myApplyToOtherGroup;
    }

    public void setMyApplyToOtherGroup (List<IEffect> myApplyToOtherGroup) {
        this.myApplyToOtherGroup = myApplyToOtherGroup;
    }

    public ISpriteGroup getMyOtherGroup () {
        return myOtherGroup;
    }

    public void setMyOtherGroup (ISpriteGroup myOtherGroup) {
        this.myOtherGroup = myOtherGroup;
    }

    public List<IEffect> getMyApplyToGlobalAttys () {
        return myApplyToGlobalAttys;
    }

    public void setMyApplyToGlobalAttys (List<IEffect> myApplyToGlobalAttys) {
        this.myApplyToGlobalAttys = myApplyToGlobalAttys;
    }

}
