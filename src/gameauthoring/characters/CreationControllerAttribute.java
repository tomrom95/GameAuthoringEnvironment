package gameauthoring.characters;

import java.util.List;
import engine.definitions.AttributeDefinition;

public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    public CreationControllerAttribute (List<? extends ISubFormController<AttributeDefinition>> subFormControllers) {
        super(subFormControllers);
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

}
