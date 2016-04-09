package gameauthoring.characters;

import java.util.List;
import engine.IProfile;
import engine.ISprite;
import engine.Profile;


public class ProfileSubFormController< T extends ISprite> implements ISubFormController<T> {

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
    private TempProfileSubFormView myView;

    public ProfileSubFormController (TempProfileSubFormView view) {
        this.myView = view;
    }

    @Override
    public void updateGameModel (T item) {
        IFormDataManager formDataWrapper = myView.getData();
        String name = formDataWrapper.getValue("name");
        String description = formDataWrapper.getValue("description");
        String imagePath = formDataWrapper.getValue("image");
        
        //TODO: edit profile property or create a new one?
        // edit
        item.getProfileProperty().get().getNameProperty().set(name);
        item.getProfileProperty().get().getDescriptionProperty().set(description);
        item.getProfileProperty().get().getImageFilepathProperty().set(imagePath);
        
        // new
        //IProfile profile = new Profile(name, description, imagePath);
        ///item.getProfileProperty().set(profile);
        
      
    }

    @Override
    public void populateViewsWithData (T item) {
        String name = item.getProfileProperty().get().getNameProperty().get();
        String description = item.getProfileProperty().get().getDescriptionProperty().get();
        String imagePath = item.getProfileProperty().get().getImageFilepathProperty().get();
        IFormDataManager formDataWrapper = myView.getData();
        formDataWrapper.add("name", name);
        formDataWrapper.add("description", description);
        formDataWrapper.add("image", imagePath);
        
        myView.populateWithData(formDataWrapper);
        
        //List<EntryView> views = myView.getMyEntryViews();
        
        //Example of how it might be done
        
        //  views.get(myView.getMyNameInd()).populateWithData(new FormData("Name: ", Sprite.getName()));
        
    }

}
