package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.SpriteGroup;


public class CreationControllerGroup extends CreationController<SpriteGroup> {

    public CreationControllerGroup (String title,
                                    List<String> sfcs,
                                    AuthorshipData authorshipData) {
        super(title, sfcs, authorshipData);
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
