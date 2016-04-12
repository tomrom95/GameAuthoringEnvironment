package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.IDefinitionCollection;



public class SelectAttributeSubFormController implements ISubFormControllerSprite {

    private SelectAttributeSubFormView myView;
    
    public SelectAttributeSubFormController(IDefinitionCollection<AttributeDefinition> attributes){
        myView = new SelectAttributeSubFormView(attributes);
    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
        AttributeDefinition selection = myView.getEntryView().getSelected();
        item.addAttribute(selection);
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        try{
            AttributeDefinition selection = item.getAttributes().get(0);
            myView.getEntryView().setSelected(selection);
        }
        catch(IndexOutOfBoundsException e){
            myView.getEntryView().setSelected(null);
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
