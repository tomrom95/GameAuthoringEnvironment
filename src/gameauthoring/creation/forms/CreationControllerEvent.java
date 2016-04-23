package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;

import gameauthoring.creation.factories.EventsSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import engine.definitions.concrete.EventPackageDefinition;


/**
 * This class controls the creation of events
 * 
 * @author Tommy, Jeremy Schreck
 *
 */
public class CreationControllerEvent extends CreationController<EventPackageDefinition> {

    public CreationControllerEvent (String title,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(title, sfcs, myGame);
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedEvents(getMyDefinitionCollection());

    }

    @Override
    protected EventPackageDefinition createBlankItem () {
        return new EventPackageDefinition();
    }

    @Override
    protected SubFormControllerFactory<EventPackageDefinition> createSFCFactory (IGame game) {
        return new EventsSFCFactory(game);
    }

}
