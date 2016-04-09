package engine.definitions;


import java.util.function.DoublePredicate;
import engine.OnGlobalAttributeCondition;



/**
 * 'Definition' class for the OnGlobalAttributeCondition
 * 
 * @author jonathanim
 *
 */
public class OnGlobalAttributeDefinition implements IDefinition {

    private GameDefinition myGame;
    
    private AttributeTypeDefinition myAttributeType;
    private DoublePredicate myValueCheck; // need to create something that will actually get this
                                          // from the gui
    private ListIEffectDefinition myApplyToSelf;
    private SpriteGroupDefinition myOtherGroup;
    private ListIEffectDefinition myApplyToOtherGroup;
    private ListIEffectDefinition myApplyToGlobalAttys;

    public OnGlobalAttributeCondition create () {
        return new OnGlobalAttributeCondition(myGame.create(), myAttributeType.create(), myValueCheck,
                                              myApplyToSelf.create(),
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
