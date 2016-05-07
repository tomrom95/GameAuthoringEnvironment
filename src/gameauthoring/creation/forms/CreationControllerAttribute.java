// This entire file is part of my masterpiece.
// Jeremy Schreck
/*
 * 
 * 
 * 
 * 
 */
package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.factories.AttributeSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.util.ListWrapper;


/**
 * This class controls the creation of character attributes
 *
 * @author Jeremy Schreck, Joe Lilien
 *
 */
public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    /**
     * Constructor
     *
     * @param key The controller's key used to get its title
     * @param subFormStrings String IDs specifying which subforms to use
     * @param game The current game object
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
    protected ListWrapper<AttributeDefinition> getItemsWrapperFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedAttributes();

    }

    @Override
    protected void deleteItem () {
        getMyAuthorshipData().removeFromAttributes(getMyCurrentItem());
        super.deleteItem();
    }

}
