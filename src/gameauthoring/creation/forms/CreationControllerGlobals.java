package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import engine.definitions.concrete.AttributeDefinition;


/**
 * Stores created attributes in global resources instead of character specific attributes collection
 * in authorship data
 * 
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class CreationControllerGlobals extends CreationController<AttributeDefinition> {

    public CreationControllerGlobals (String title, List<String> subFormStrings, IGame myGame) {
        super(title, subFormStrings, myGame);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedGlobals(getMyDefinitionCollection());
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

    @Override
    protected SubFormControllerFactory<AttributeDefinition> createSFCFactory (IGame game) {
        return new AttributeSFCFactory(game);
    }

}
