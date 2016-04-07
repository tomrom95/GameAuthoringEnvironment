package gameauthoring.characters;

import java.util.List;
import engine.ISprite;


public class ProfileSubFormController implements ISubFormController {

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
    public void updateGameModel (ISprite sprite) {
        IFormDataManager formDataWrapper = myView.getData();
        String name = formDataWrapper.getValue("name");
        String description = formDataWrapper.getValue("description");
        String image = formDataWrapper.getValue("image");
        
        /*
         Old code
        List<FormData> data = myView.getData();
        String Name = data.get(myView.getMyNameInd()).getMyValue().get(0);
        String Description = data.get(myView.getMyDescriptionInd()).getMyValue().get(0);
        String Image = data.get(myView.getMyImageInd()).getMyValue().get(0);
        */
        
        
        /*
         * **Need to fill in with actual method calls**
         * 
         * sprite.setName(name)
         * sprite.setDescription(description)
         * sprite.setImage(image)
         */
    }

    @Override
    public void populateViewsWithData () {
        //myView.populateWithData(myFormDataManager);
        
        List<EntryView> views = myView.getMyEntryViews();
        
        //Example of how it might be done
        
        //  views.get(myView.getMyNameInd()).populateWithData(new FormData("Name: ", Sprite.getName()));
        
    }

}
