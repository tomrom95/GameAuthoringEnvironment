package gameauthoring.characters;

import java.util.List;
import engine.definitions.SpriteDefinition;

public class CreationControllerSprite extends CreationController<SpriteDefinition> {

    public CreationControllerSprite (List<? extends ISubFormController<SpriteDefinition>> subFormControllers) {
        super(subFormControllers);
    }

    @Override
    protected SpriteDefinition createBlankItem () {
        return new SpriteDefinition();
    }

}
