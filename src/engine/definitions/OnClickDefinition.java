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
    private EventPackageDefinition mySelfPackage;
    private EventPackageDefinition myOtherPackage;
    private EventPackageDefinition myGlobalPackage;

    public OnClickCondition create () {
        return new OnClickCondition(myGame.create(), mySelfPackage.create(),
                                    myOtherPackage.create(), myGlobalPackage.create());
    }

    public GameDefinition getMyGame () {
        return myGame;
    }

    public void setMyGame (GameDefinition game) {
        this.myGame = game;
    }

    public EventPackageDefinition getMySelfPackage () {
        return mySelfPackage;
    }

    public void setMySelfPackage (EventPackageDefinition selfPackage) {
        this.mySelfPackage = selfPackage;
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
