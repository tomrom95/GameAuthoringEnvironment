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
    private AttributeTypeDefinition myAttributeType;
    private DoublePredicate myValueCheck;

    private EventPackageDefinition mySpritePackage;
    private EventPackageDefinition myOtherPackage;
    private EventPackageDefinition myGlobalPackage;

    public OnSpriteAttributeCondition create () {
        return new OnSpriteAttributeCondition(myGame.create(), 
                                              myAttributeType.create(), 
                                              myValueCheck,
                                              mySpritePackage.create(),
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

    public EventPackageDefinition getMySpritePackage () {
        return mySpritePackage;
    }

    public void setMySpritePackage (EventPackageDefinition spritePackage) {
        this.mySpritePackage = spritePackage;
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
