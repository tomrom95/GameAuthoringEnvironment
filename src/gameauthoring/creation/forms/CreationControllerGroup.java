package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import engine.SpriteGroup;


public class CreationControllerGroup extends CreationController<SpriteGroup> {

    public CreationControllerGroup (String title,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(title, sfcs, myGame);
        // TODO: Change back to this to fix casting issue
        // setMySubFormControllers(getMySFCFactory().createSpriteSubFormControllers(subFormStrings));

    }

    @Override
    protected SpriteGroup createBlankItem () {
        return new SpriteGroup();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedGroups(getMyDefinitionCollection());
    }

}
