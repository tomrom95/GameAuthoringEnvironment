package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.SpriteGroup;
import gameauthoring.creation.factories.GroupSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.shareddata.DefinitionCollection;


public class CreationControllerGroup extends CreationController<SpriteGroup> {

    public CreationControllerGroup (String key,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(key, sfcs, myGame);
    }

    @Override
    protected SpriteGroup createBlankItem () {
        return new SpriteGroup();
    }

    @Override
    protected SubFormControllerFactory<SpriteGroup> createSFCFactory (IGame game) {
        return new GroupSFCFactory(game);
    }

    @Override
    protected DefinitionCollection<SpriteGroup> getDefinitionCollectionFromAuthorshipData (AuthorshipData authorshipData) {
        return authorshipData.getMyCreatedGroups();
    }

}
