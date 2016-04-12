package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;

/**
 * This class controls the creation of Sprites
 * @author jeremy
 *
 */
public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    public CreationControllerSprite (String title, List<String> subFormStrings,
                                     AuthorshipData authorshipData) {
        super(title, subFormStrings, authorshipData);
        //TODO: Change back to this to fix casting issue
        //setMySubFormControllers(getMySFCFactory().createSpriteSubFormControllers(subFormStrings));

        //Hook up observable list of items to authorship data so level view can have access
        DefinitionCollection<SpriteDefinition> defCol = new DefinitionCollection<SpriteDefinition>(getMyTitle(), getMyObjectCreationView().getItems());
        authorshipData.addCreatedSprites(defCol);
    }
    public void init(List<String> sfcs) {
        super.init(sfcs);
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        return new SpriteDefinition();
    }

}
