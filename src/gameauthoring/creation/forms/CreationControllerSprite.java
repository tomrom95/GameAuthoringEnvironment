package gameauthoring.creation.forms;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
    
    public CreationControllerSprite (String key,
                                     List<String> subFormStrings,
                                     IGame myGame) {
        super(key, subFormStrings, myGame);
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        return new SpriteDefinition();
    }

    @Override
    protected SubFormControllerFactory<SpriteDefinition> createSFCFactory (IGame game) {
        return new SpriteSFCFactory(game);
    }

    @Override
    protected DefinitionCollection<SpriteDefinition> getDefinitionCollectionFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedSprites(getMyKey(), getMyTitle());
    }

}
