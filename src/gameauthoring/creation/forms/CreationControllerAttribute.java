package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.AttributeDefinition;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;


/**
 * This class controls the creation of attributes
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 */
public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    /**
     * Constructor
     * 
     * @param title The controller's title
     * @param subFormStrings Strings specifying which subforms to use
     * @param authorshipData Data shared by authoring elements
     */
    public CreationControllerAttribute (String title,
                                        List<String> subFormStrings,
                                        IGame game) {
        super(title, subFormStrings, game);

    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedAttributes(getMyDefinitionCollection());
    }

    @Override
    protected SubFormControllerFactory<AttributeDefinition> createSFCFactory (IGame game) {
        return new AttributeSFCFactory(game);

    }

}
