package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import gameauthoring.DefinitionCollection;
import gameauthoring.creation.subforms.ISubFormController;


public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    public CreationControllerAttribute (String title, List<String> subFormStrings,
                                        AuthorshipData authorshipData) {
        super(title, subFormStrings, authorshipData);
        //setMySubFormControllers(getMySFCFactory().createAttributeSubFormControllers(subFormStrings));

        DefinitionCollection<AttributeDefinition> defCol = new DefinitionCollection<AttributeDefinition>(getMyTitle(), getMyObjectCreationView().getItems());
        authorshipData.setMyCreatedAttributes(defCol);
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

}
