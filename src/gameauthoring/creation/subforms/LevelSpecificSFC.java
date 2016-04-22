package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;

public class LevelSpecificSFC implements ISubFormControllerAttribute{
    
    private LevelSpecificSFV myView;
    
    public LevelSpecificSFC(){
        myView = new LevelSpecificSFV();
    }

    @Override
    public void updateItem (AttributeDefinition item) {
        item.setIsLevelSpecific(myView.isLevelSpecific());
    }

    @Override
    public void initializeFields () {        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
