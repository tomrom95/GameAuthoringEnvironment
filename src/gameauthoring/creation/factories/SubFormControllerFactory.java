package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ProfileSFC;
import gameauthoring.creation.subforms.events.EventsSubFormController;
import gameauthoring.creation.subforms.fire.FiringSubFormController;
import gameauthoring.creation.subforms.movement.MovementSubFormController;


public abstract class SubFormControllerFactory<T extends IProfilable> {

    private IGame myGame;

    public SubFormControllerFactory (IGame game) {

        myGame = game;
    }

    public List<ISubFormController<T>> createSubFormControllers (List<String> subFormStrings) {
        List<ISubFormController<T>> list = new ArrayList<>();
        for (String subFormString : subFormStrings) {
            if(subFormString.equals("ProfileSFC")){
                list.add(createProfileSFC());
            }else{
                list.add(createSubFormController(subFormString));
            }
        }
        return list;

    }
   
    protected abstract ISubFormController<T> createSubFormController (String type);

    protected IGame getMyGame () {
        return myGame;
    }

    public AuthorshipData getMyAuthorshipData () {
        return getMyGame().getAuthorshipData();
    }

    public ProfileSFC<T> createProfileSFC () {
        return new ProfileSFC<T>();
    }

}
