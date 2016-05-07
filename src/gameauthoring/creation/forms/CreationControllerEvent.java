package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.factories.EventsSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.util.ListWrapper;


/**
 * This class controls the creation of event packages
 *
 * @author Tommy, Jeremy Schreck
 *
 */
public class CreationControllerEvent extends CreationController<EventPackageDefinition> {

    public CreationControllerEvent (String key,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(key, sfcs, myGame);
    }

    @Override
    protected EventPackageDefinition createBlankItem () {
        return new EventPackageDefinition();
    }

    @Override
    protected SubFormControllerFactory<EventPackageDefinition> createSFCFactory (IGame game) {
        return new EventsSFCFactory(game);
    }

    @Override
    protected ListWrapper<EventPackageDefinition> getItemsWrapperFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedEventPackages();
    }

}
