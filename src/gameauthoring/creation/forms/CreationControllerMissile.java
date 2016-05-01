package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import engine.profile.Profile;
import gameauthoring.shareddata.DefinitionCollection;


public class CreationControllerMissile extends CreationControllerSprite {

    public CreationControllerMissile (String key,
                                      List<String> subFormStrings,
                                      IGame myGame) {
        super(key, subFormStrings, myGame);

    }

    @Override
    protected DefinitionCollection<SpriteDefinition> getDefinitionCollectionFromAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.getMyCreatedGroups()
                .addItem(new SpriteGroup(authorshipData.getMyCreatedMissiles().getItems(),
                                         new Profile(getMyTitle(), "",
                                                     getImageBundle().getString(getMyKey()))));
        return authorshipData.getMyCreatedMissiles();
    }

}
