package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.IDefinitionCollection;


public class SelectAttributeSubFormController implements ISubFormControllerSprite {

    private SelectAttributeSubFormView myView;

    public SelectAttributeSubFormController (IDefinitionCollection<AttributeDefinition> attributes) {
        myView = new SelectAttributeSubFormView(attributes);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        item.setAttributes(myView.getEntryView().getSelected());
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.getEntryView().setSelected(item.getAttributes());

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
