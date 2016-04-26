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
        //authorshipData.addCreatedSprites(getMyDefinitionCollection());
        //TODO need to make createdSprites a map so I can get Enemies and Defenders separately        
        // other option is to make subclasses of creationcontroller sprite for each one
        
        //TODO: super hacky fix for now
//        if(getMyTitle().equals(myLang.getString("Enemies"))){
//            setMyDefinitionCollection(authorshipData.getMyCreatedSprites().get(0));
//        }
//        else if(getMyTitle().equals(myLang.getString("Defenders"))){
//            setMyDefinitionCollection(authorshipData.getMyCreatedSprites().get(1));
//        }
        
        setMyDefinitionCollection(authorshipData.getMyCreatedSprites().get(0));

        
        authorshipData.addGroupSprites(getMyDefinitionCollection());
        
        //uncomment if we want a different list for level selector sprites than created sprites
        //authorshipData.addLevelSelectorSprites(getMyDefinitionCollection());
    }

    @Override
    protected SubFormControllerFactory<SpriteDefinition> createSFCFactory (IGame game) {
        return new SpriteSFCFactory(game);
    }

}
