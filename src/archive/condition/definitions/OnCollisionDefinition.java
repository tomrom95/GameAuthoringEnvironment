package archive.condition.definitions;

import engine.conditions.OnCollisionCondition;
import engine.definitions.EventPackageDefinition;
import engine.definitions.GameDefinition;
import engine.definitions.IDefinition;


/**
 * 'Definition' class for the OnCollisionCondition
 *
 * @author jonathanim
 *
 */
public class OnCollisionDefinition implements IDefinition {

    private GameDefinition myGame;
    private EventPackageDefinition myGroupAPackage;
    private EventPackageDefinition myGroupBPackage;
    private EventPackageDefinition myOtherPackage;
    private EventPackageDefinition myGlobalPackage;

    public OnCollisionCondition create () {
        return new OnCollisionCondition(myGame.create(),
                                        myGroupAPackage.create(),
                                        myGroupBPackage.create(),
                                        myOtherPackage.create(),
                                        myGlobalPackage.create());
    }

    public GameDefinition getMyGame () {
        return myGame;
    }

    public void setMyGame (GameDefinition game) {
        this.myGame = game;
    }

    public EventPackageDefinition getMyGroupAPackage () {
        return myGroupAPackage;
    }

    public void setMyGroupAPackage (EventPackageDefinition groupAPackage) {
        this.myGroupAPackage = groupAPackage;
    }

    public EventPackageDefinition getMyGroupBPackage () {
        return myGroupBPackage;
    }

    public void setMyGroupBPackage (EventPackageDefinition groupBPackage) {
        this.myGroupBPackage = groupBPackage;
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
