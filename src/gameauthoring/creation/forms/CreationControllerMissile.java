package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;


public class CreationControllerMissile extends CreationControllerSprite {

    public CreationControllerMissile (String title,
                                      List<String> subFormStrings,
                                      IGame myGame) {
        super(title, subFormStrings, myGame);

    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        //authorshipData.setMyCreatedMissiles(getMyDefinitionCollection());
        setMyDefinitionCollection(authorshipData.getMyCreatedMissiles());
        
        
        
        authorshipData.addGroupSprites(getMyDefinitionCollection());
        
        //uncomment if missiles should be included in created sprites
        //authorshipData.addCreatedSprites(getMyDefinitionCollection()); 
    }

}
