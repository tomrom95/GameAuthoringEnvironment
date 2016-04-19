package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.IDefinitionCollection;

/**
 * Controller to deal with user attribute selection for a sprite definition
 * 
 * @author Joe Lilien
 *
 */
public class SelectAttributeSFC implements ISubFormControllerSprite {

    private SelectAttributeSFV myView;

    public SelectAttributeSFC (IDefinitionCollection<AttributeDefinition> attributes) {
        myView = new SelectAttributeSFV(attributes);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        item.setAttributes(myView.getSelectedAttributes());
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields () {

    }

}
