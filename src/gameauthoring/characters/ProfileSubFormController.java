package gameauthoring.characters;

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
     * Maybe best solution is to interface or superclass ProfileSubFormView and just have it take
     * any
     * implementation of that in constructor, but still issues to work out (current Implementation)
     * 
     */
    private ProfileSubFormView myView;
    private IFormDataManager myFormData;

    public ProfileSubFormController () {
        this.myView = new ProfileSubFormView();
        this.myFormData = myView.getData();
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        String name = myFormData.getValueProperty(myView.getMyNameKey()).get();
        String desc = myFormData.getValueProperty(myView.getMyDescriptionKey()).get();
        String url = myFormData.getValueProperty(myView.getMyImageKey()).get();
        
        item.setName(name);
        item.setDescription(desc);
        item.setURL(url);

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {        
        myFormData.set(myView.getMyNameKey(), item.getName());
        myFormData.set(myView.getMyDescriptionKey(), item.getDescription());
        
        myFormData.set(myView.getMyImageKey(), item.getURL());
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
