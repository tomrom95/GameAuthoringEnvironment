package gameauthoring.characters;

import java.util.List;
import engine.definitions.ConditionDefinition;

public class CreationControllerCondition extends CreationController<ConditionDefinition> {

    public CreationControllerCondition (List<ISubFormController<ConditionDefinition>> subFormControllers) {
        super(subFormControllers);
    }

    @Override
    protected ConditionDefinition createBlankItem () {
        //return new ConditionDefinition();
        return null
    }

}
