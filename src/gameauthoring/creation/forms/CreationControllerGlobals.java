package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.util.ListWrapper;


/**
 * This class controls the creation of global attributes
 *
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class CreationControllerGlobals extends CreationController<AttributeDefinition> {

    public CreationControllerGlobals (String key, List<String> subFormStrings, IGame myGame) {
        super(key, subFormStrings, myGame);
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

    @Override
    protected SubFormControllerFactory<AttributeDefinition> createSFCFactory (IGame game) {
        return new AttributeSFCFactory(game);
    }

    @Override
    protected ListWrapper<AttributeDefinition> getItemsWrapperFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedGlobals();
    }

}
