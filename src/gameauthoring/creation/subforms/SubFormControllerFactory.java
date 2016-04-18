package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.fire.DirectionalFireSubFormController;
import gameauthoring.creation.subforms.fire.FiringSubFormController;
import gameauthoring.creation.subforms.fire.TrackingFireSubFormController;
import gameauthoring.creation.subforms.movement.MovementSubFormController;
import gameauthoring.creation.subforms.movement.SmartAIMovementSubFormController;
import gameauthoring.creation.subforms.movement.UserMoverSubFormController;


public class SubFormControllerFactory {

    private IGame myGame;

    public SubFormControllerFactory (Game game) {

        myGame = game;
    }

    // general

    public List<ISubFormController<?>> createSubFormControllers (List<String> subFormStrings) {
        List<ISubFormController<?>> list = new ArrayList<ISubFormController<?>>();
        for (String subFormString : subFormStrings) {
            list.add(createSubFormController(subFormString));
        }
        return list;

    }

    private ISubFormController<?> createSubFormController (String type) {
        if (type.equals("Profile")) {
            System.out.println("profile");
            // return new ProfileSubFormController();

        }
        else if (type.equals("Movement")) {
            return new MovementSubFormController();
        }
        else if (type.equals("SmartAI")) {
            System.out.println("smartAI");

            return new SmartAIMovementSubFormController();
        }
        else if (type.equals("UserMover")) {
            System.out.println("userMover");

            return new UserMoverSubFormController();
        }
        else if (type.equals("SelectAttribute")) {
            return new SelectAttributeSubFormController(getMyAuthorshipData()
                    .getMyCreatedAttributes());
        }
        else if (type.equals("Attribute")) {
            return new MakeAttributeSubFormController();
        }
        else if (type.equals("Events")) {
            return new EventsSubFormController(getMyAuthorshipData().getMyCreatedAttributes(),
                                               getMyAuthorshipData().getMyCreatedEventPackages());
        }
        else if (type.equals("SelectSprite")) {
            return new SelectSpriteSFC(getMyAuthorshipData().getMyCreatedSprites());
        }
        else if (type.equals("Firing")) {
            return new FiringSubFormController(myGame);
        }
        else if (type.equals("DirectionalFire")) {
            return new DirectionalFireSubFormController(getMyGame());
        }
        else if (type.equals("TrackingFire")) {
            return new TrackingFireSubFormController(getMyGame());
        }
        else if (type.equals("Upgrade")) {
            return new UpgradeSFC(getMyGame());
        }
        System.out.println("null");

        return null;
    }

    // Non general
    public List<ISubFormControllerSprite> createSpriteSubFormControllers (List<String> subFormStrings) {
        List<ISubFormControllerSprite> list = new ArrayList<ISubFormControllerSprite>();
        for (String subFormString : subFormStrings) {
            list.add(createSpriteSubFormController(subFormString));
        }
        return list;
    }

    public ISubFormControllerSprite createSpriteSubFormController (String type) {

        if (type.equals("Profile")) {
            // return new ProfileSubFormController();

        }

        else if (type.equals("Movement")) {
            return new MovementSubFormController();
        }

        else if (type.equals("SmartAI")) {

            return new SmartAIMovementSubFormController();
        }
        else if (type.equals("UserMover")) {

            return new UserMoverSubFormController();
        }
        else if (type.equals("SelectAttribute")) {
            return new SelectAttributeSubFormController(getMyAuthorshipData()
                    .getMyCreatedAttributes());
        }
        else if (type.equals("Firing")) {
            return new FiringSubFormController(myGame);
        }
        else if (type.equals("DirectionalFire")) {
            return new DirectionalFireSubFormController(getMyGame());
        }
        else if (type.equals("TrackingFire")) {
            return new TrackingFireSubFormController(getMyGame());
        }
        System.out.println("null");

        return null;
    }

    public List<ISubFormControllerAttribute> createAttributeSubFormControllers (List<String> subFormStrings) {
        List<ISubFormControllerAttribute> list = new ArrayList<ISubFormControllerAttribute>();
        for (String subFormString : subFormStrings) {
            list.add(createAttributeSubFormController(subFormString));
        }
        return list;
    }

    public ISubFormControllerAttribute createAttributeSubFormController (String type) {
        if (type.equals("Attribute")) {
            return new MakeAttributeSubFormController();
        }
        System.out.println("null");

        return null;
        // return new AttributeSubFormController();
    }

    private IGame getMyGame () {
        return myGame;
    }

    public AuthorshipData getMyAuthorshipData () {
        return getMyGame().getAuthorshipData();
    }

    public ProfileSubFormController<IProfilable> createProfileSFC () {
        return new ProfileSubFormController<IProfilable>();
    }

}
