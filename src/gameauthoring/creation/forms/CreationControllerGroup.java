package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.SpriteGroup;
import gameauthoring.creation.factories.GroupSFCFactory;
import gameauthoring.creation.factories.SubFormControllerFactory;


public class CreationControllerGroup extends CreationController<SpriteGroup> {

    public CreationControllerGroup (String title,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(title, sfcs, myGame);
    }

    @Override
    protected SpriteGroup createBlankItem () {
        return new SpriteGroup();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedGroups(getMyDefinitionCollection());
    }

    @Override
    protected SubFormControllerFactory<SpriteGroup> createSFCFactory (IGame game) {
        // TODO Auto-generated method stub
        return new GroupSFCFactory(game);
    }

}
