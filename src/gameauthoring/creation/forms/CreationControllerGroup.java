package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.SpriteGroup;
import gameauthoring.shareddata.DefinitionCollection;

public class CreationControllerGroup extends CreationController<SpriteGroup>{

    public CreationControllerGroup (String title,
                                    List<String> sfcs,
                                    AuthorshipData authorshipData) {
        super(title, sfcs, authorshipData);
        //TODO: Change back to this to fix casting issue
        //setMySubFormControllers(getMySFCFactory().createSpriteSubFormControllers(subFormStrings));

        //Hook up observable list of items to authorship data so level view can have access
        DefinitionCollection<SpriteGroup> createdGroups = new DefinitionCollection<>(getMyTitle(), getMyObjectCreationView().getItems());
        authorshipData.setMyCreatedGroups(createdGroups);
    }

    @Override
    protected SpriteGroup createBlankItem () {
        return new SpriteGroup();
    }

}
