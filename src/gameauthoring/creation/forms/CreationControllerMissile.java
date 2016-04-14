package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;


public class CreationControllerMissile extends CreationControllerSprite {

    public CreationControllerMissile (String title,
                                      List<String> subFormStrings,
                                      Game game) {
        super(title, subFormStrings, game);

    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedMissiles(getMyDefinitionCollection());
    }

}
