package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import gameauthoring.IDefinitionCollection;
import gameauthoring.creation.subforms.ISubFormController;


public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    public CreationControllerAttribute (List<? extends ISubFormController<AttributeDefinition>> subFormControllers,
                                        IDefinitionCollection<AttributeDefinition> defCol) {
        super(subFormControllers, defCol);
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

}
