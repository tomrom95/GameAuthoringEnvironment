// This entire file is part of my masterpiece.
// Jeremy Schreck
/*
 * I included this class to show an example of what a subclass of creation 
 * controller looks like. There is very little code in here, demonstrating
 * how much duplicated code the superclass allows us to save.
 * 
 * Only 3 abstract methods need to be implemented
 *   1. createBlankItem: this is necessary because a new instance of a generic
 *   type cannot be instantiated in the generic superclass.
 *   
 *   2. createSFCFactory: this instantiates the factory class to use to generate
 *   the subform classes that will make up the form in the creation controller.
 *   
 *   3. getItemsWrapperFromAuthorshipData: this allows the subclass to connect
 *   its list of items to authorship data, so that the list of items it creates
 *   can be accessed by other parts of the authoring environment (ex: When 
 *   you create a bunch of "Enemy" sprites in this class, the same sprites show 
 *   up when you are specifying which sprites to place in a level)
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
