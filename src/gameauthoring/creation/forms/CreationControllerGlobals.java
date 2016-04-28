package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.shareddata.DefinitionCollection;
import engine.definitions.concrete.AttributeDefinition;


/**
 * Stores created attributes in global resources instead of character specific attributes collection
 * in authorship data
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
    protected DefinitionCollection<AttributeDefinition> getDefinitionCollectionFromAuthorshipData (AuthorshipData authorshipData) {
        // TODO Auto-generated method stub
        return authorshipData.getMyCreatedGlobals(getMyTitle());
    }

}
