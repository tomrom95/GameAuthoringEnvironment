package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;

import gameauthoring.creation.factories.SpriteSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;


/**
 * This class controls the creation of Sprite Definitions
 * 
 * @author Jeremy Schreck
 *
 */
public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    public CreationControllerSprite (String title,
                                     List<String> subFormStrings,
                                     IGame myGame) {
        super(title, subFormStrings, myGame);
    }

    public void init (List<String> sfcs) {
        super.init(sfcs);
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        return new SpriteDefinition();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.addCreatedSprites(getMyDefinitionCollection());
        authorshipData.addGroupSprites(getMyDefinitionCollection());
        
        //uncomment if we want a different list for level selector sprites than created sprites
        //authorshipData.addLevelSelectorSprites(getMyDefinitionCollection());
    }

    @Override
    protected SubFormControllerFactory<SpriteDefinition> createSFCFactory (IGame game) {
        return new SpriteSFCFactory(game);
    }

}
