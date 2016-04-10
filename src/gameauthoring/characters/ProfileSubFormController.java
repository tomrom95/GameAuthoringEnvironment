package gameauthoring.characters;

import java.util.List;
import engine.IProfile;
import engine.sprite.ISprite;
import engine.Profile;
import engine.definitions.ProfileDefinition;
import engine.definitions.SpriteDefinition;



public class ProfileSubFormController implements ISubFormControllerSprite {

    /**
     * **Implementation still up for discussion
     * 
     * We could either have a third party XML reader associate all controllers and views or instead
     * do it like this and just have a get view method so the FormView can just get a list of views.
     * 
     * Hidden Dependencies VS Flexibility
     * 
     * Maybe best solution is to interface or superclass ProfileSubFormView and just have it take any
     * implementation of that in constructor,  but still issues to work out  (current Implementation)
     * 
     */
    private ProfileSubFormView myView;
    private IFormDataManager myFormData;

    public ProfileSubFormController () {
        this.myView = new ProfileSubFormView();
        this.myFormData = myView.getData();
    }

 

    @Override
    public void updateGameModel (SpriteDefinition item) {
        item.setName(myFormData.getValueProperty(myView.getMyNameKey()).get());
        item.setURL(myFormData.getValueProperty(myView.getMyImageKey()).get());
        //Same process for description
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
       myFormData.set(myView.getMyNameKey(), item.getName());
       myFormData.set(myView.getMyImageKey(), item.getURL());
       // Same Process for Description
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }
    

}
