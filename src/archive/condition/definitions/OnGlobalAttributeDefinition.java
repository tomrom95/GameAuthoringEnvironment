package archive.condition.definitions;

import java.util.function.DoublePredicate;
import engine.conditions.OnGlobalAttributeCondition;
import engine.definitions.AttributeTypeDefinition;
import engine.definitions.EventPackageDefinition;
import engine.definitions.GameDefinition;
import engine.definitions.IDefinition;


/**
 * 'Definition' class for the OnGlobalAttributeCondition
 *
 * @author jonathanim
 *
 */
public class OnGlobalAttributeDefinition implements IDefinition {

    private GameDefinition myGame;
    private AttributeTypeDefinition myAttributeType;
    private DoublePredicate myValueCheck;
    private EventPackageDefinition myOtherPackage;
    private EventPackageDefinition myGlobalPackage;

    public OnGlobalAttributeCondition create () {
        return new OnGlobalAttributeCondition(myGame.create(), myAttributeType.create(),
                                              myValueCheck,
                                              myOtherPackage.create(),
                                              myGlobalPackage.create());
    }

    public GameDefinition getMyGame () {
        return myGame;
    }

    public void setMyGame (GameDefinition game) {
        this.myGame = game;
    }

    public AttributeTypeDefinition getMyAttributeType () {
        return myAttributeType;
    }

    public void setMyAttributeType (AttributeTypeDefinition attributeType) {
        this.myAttributeType = attributeType;
    }

    public DoublePredicate getMyValueCheck () {
        return myValueCheck;
    }

    public void setMyValueCheck (DoublePredicate valueCheck) {
        this.myValueCheck = valueCheck;
    }

    public EventPackageDefinition getMyOtherPackage () {
        return myOtherPackage;
    }

    public void setMyOtherPackage (EventPackageDefinition otherPackage) {
        this.myOtherPackage = otherPackage;
    }

    public EventPackageDefinition getMyGlobalPackage () {
        return myGlobalPackage;
    }

    public void setMyGlobalPackage (EventPackageDefinition globalPackage) {
        this.myGlobalPackage = globalPackage;
    }

}
