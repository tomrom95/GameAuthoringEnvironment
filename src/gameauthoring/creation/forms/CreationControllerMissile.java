package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;


public class CreationControllerMissile extends CreationControllerSprite {

    public CreationControllerMissile (String title,
                                      List<String> subFormStrings,
                                      AuthorshipData authorshipData) {
        super(title, subFormStrings, authorshipData);

    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedMissiles(getMyDefinitionCollection());
    }

}
