package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.shareddata.IDefinitionCollection;


/**
 * Controller to deal with user attribute selection for a sprite definition
 *
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class SelectAttributeSFC implements ISubFormControllerSprite {

    private ISelectAttributeSFV myView;

    public SelectAttributeSFC (IDefinitionCollection<AttributeDefinition> attributes) {
        myView = new SelectAttributeSFV(attributes);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        item.setAttributes(myView.getSelectedAttributes());
    }

    @Override
    public void initializeFields () {

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.setSelectedAttributes(item.getAttributes());

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
