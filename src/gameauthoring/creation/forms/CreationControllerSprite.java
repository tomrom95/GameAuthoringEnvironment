package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.DefinitionCollection;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;

public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    public CreationControllerSprite (String title, List<String> subFormStrings,
                                     AuthorshipData authorshipData) {
        super(title, subFormStrings, authorshipData);
        //TODO: Change factory to be generic and move this line to superclass
        //setMySubFormControllers(getMySFCFactory().createSpriteSubFormControllers(subFormStrings));

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
