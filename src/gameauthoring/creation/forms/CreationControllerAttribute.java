package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import gameauthoring.shareddata.DefinitionCollection;


/**
 * This class controls the creation of attributes
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 */
public class CreationControllerAttribute extends CreationController<AttributeDefinition> {

    /**
     * Constructor
     * 
     * @param title The controller's title
     * @param subFormStrings Strings specifying which subforms to use
     * @param authorshipData Data shared by authoring elements
     */
    public CreationControllerAttribute (String title,
                                        List<String> subFormStrings,
                                        AuthorshipData authorshipData) {
        super(title, subFormStrings, authorshipData);
        // TODO: add this back
        // setMySubFormControllers(getMySFCFactory().createAttributeSubFormControllers(subFormStrings));

    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {

        DefinitionCollection<AttributeDefinition> defCol =
                new DefinitionCollection<AttributeDefinition>(getMyTitle(),
                                                              getMyObjectCreationView().getItems());
        authorshipData.setMyCreatedAttributes(defCol);

    }

}
