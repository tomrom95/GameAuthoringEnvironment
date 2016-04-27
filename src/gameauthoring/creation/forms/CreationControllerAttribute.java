package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.shareddata.DefinitionCollection;


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
     * @param key The controller's key used to get its title
     * @param subFormStrings Strings specifying which subforms to use
     * @param authorshipData Data shared by authoring elements
     */
    public CreationControllerAttribute (String key,
                                        List<String> subFormStrings,
                                        IGame game) {
        super(key, subFormStrings, game);

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
        return authorshipData.getMyCreatedAttributes(getMyTitle());

    }

}
