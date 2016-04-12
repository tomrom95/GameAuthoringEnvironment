package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;

public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    public CreationControllerSprite (List<? extends ISubFormController<SpriteDefinition>> subFormControllers) {
        super(subFormControllers);
    }

    public CreationControllerSprite (List<? extends ISubFormController<SpriteDefinition>> subFormControllers,
                                     AuthorshipData authorshipData) {
        super(subFormControllers, authorshipData);
        
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        return new SpriteDefinition();
    }

}
