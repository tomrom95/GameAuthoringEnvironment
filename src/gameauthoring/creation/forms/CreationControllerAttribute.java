package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
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
                                        IGame myGame) {
        super(title, subFormStrings, myGame);
        // TODO: add this back
        // setMySubFormControllers(getMySFCFactory().createAttributeSubFormControllers(subFormStrings));

    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {

        authorshipData.setMyCreatedAttributes(getMyDefinitionCollection());

    }

}
