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
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.factories.SpriteSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.util.ListWrapper;


/**
 * This class controls the creation of Sprite Definitions
 *
 * @author Jeremy Schreck, Joe Lilien
 *
 */
public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    /**
     * Constructor
     *
     * @param key The controller's key used to get its title
     * @param subFormStrings String IDs specifying which subforms to use
     * @param game The current game object
     */
    public CreationControllerSprite (String key,
                                     List<String> subFormStrings,
                                     IGame game) {
        super(key, subFormStrings, game);
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        SpriteDefinition item = new SpriteDefinition();
        SpriteGroup group = new SpriteGroup(item, item.getProfile());
        item.setMySingleGroup(group);
        getMyAuthorshipData().getMyCreatedGroups().getItems().add(group);
        return item;
    }

    @Override
    protected SubFormControllerFactory<SpriteDefinition> createSFCFactory (IGame game) {
        return new SpriteSFCFactory(game);
    }

    @Override
    protected ListWrapper<SpriteDefinition> getItemsWrapperFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedSprites(getMyKey());
    }

    @Override
    protected void deleteItem () {
        getMyAuthorshipData().removeFromGroups(getMyCurrentItem());
        getMyAuthorshipData().getMyCreatedGroups().getItems().remove(getMyCurrentItem().getMySingleGroup());
        super.deleteItem();

    
    }
        


}
