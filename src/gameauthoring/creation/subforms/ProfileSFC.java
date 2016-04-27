package gameauthoring.creation.subforms;

import engine.profile.IProfilable;
import engine.profile.IProfile;
import gameauthoring.creation.entryviews.IFormDataManager;


/**
 * Controls definition of profile for all objects that can be created in authoring, designates a
 * name, description and image for a given profilable object
 * 
 * @author Joe Lilien
 * @author Jeremy Shreck
 *
 * @param <T> The type of object - must be profilable
 */
public class ProfileSFC<T extends IProfilable> implements ISubFormController<T> {

    private IProfileSFV myView;
    private IFormDataManager myFormData;
    private String myDefaultName = "<Name>"; // TODO: move strings to resource file
    private String myDefaultDescription = "<Description>";
    private String myDefaultImage = "images/square.png";    

    public ProfileSFC () {
        this.myView = new ProfileSFV();
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultName, myDefaultDescription, myDefaultImage);

    }

    private void populateViewsWithData (String name, String desc, String url) {
        myFormData.set(myView.getMyNameKey(), name);
        myFormData.set(myView.getMyDescriptionKey(), desc);
        myFormData.set(myView.getMyImageKey(), url);        
    }

    @Override
    public void updateItem (T item) {
        String name = myFormData.getValueProperty(myView.getMyNameKey()).get();
        String desc = myFormData.getValueProperty(myView.getMyDescriptionKey()).get();
        String url = myFormData.getValueProperty(myView.getMyImageKey()).get();
        double width = myView.getMyImageWidth();
        double height = myView.getMyImageHeight();
        item.getProfile().setNew(name, desc, url, width, height);

    }

    @Override
    public void populateViewsWithData (T item) {
       IProfile profile = item.getProfile();
       populateViewsWithData(profile.getName().get(), profile.getDescription().get(), profile.getImageURL());
    }
    
    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
