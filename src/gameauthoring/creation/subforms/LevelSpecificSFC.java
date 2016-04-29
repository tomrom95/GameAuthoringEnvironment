package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;

public class LevelSpecificSFC implements ISubFormControllerAttribute{
    
    private ILevelSpecificSFV myView;
    
    public LevelSpecificSFC(){
        myView = new LevelSpecificSFV();
    }

    @Override
    public void updateItem (AttributeDefinition item) {
        item.setIsLevelSpecific(myView.isLevelSpecific());
    }

    @Override
    public void populateViewsWithData (AttributeDefinition item) {
        myView.setLevelSpecific(item.getIsLevelSpecific());
    }
    
    @Override
    public void initializeFields (AttributeDefinition item) {        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

   

}
