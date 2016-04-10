package gameauthoring.characters;

import engine.definitions.SpriteDefinition;

public class SubFormControllerMovement implements ISubFormControllerSprite {

    private ISubFormView mySubFormView;
    
    public SubFormController(Arrangment arrangement){
        mySubFormView = new MovementSubFormView(arrangment);
        
    }
    @Override
    public ISubFormView getSubFormView () {
        return mySubFormView;
    }

    @Override
    public void updateGameModel (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

}
