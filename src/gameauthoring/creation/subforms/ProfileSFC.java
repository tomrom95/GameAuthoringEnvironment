package gameauthoring.creation.subforms;

import engine.profile.IProfilable;
import engine.profile.IProfile;


/**
 * Controls definition of profile for all objects that can be created in authoring, designates a
 * name, description and image for a given profilable object
 * 
 * @author Joe Lilien
 * @author Jeremy Shreck
 *
 * @param <T>
 */
public class ProfileSFC<T extends IProfilable> implements ISubFormController<T> {

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
    private IProfileSFV myView;
    private String myDefaultName = "<Name>"; // TODO: move strings to resource file
    private String myDefaultDescription = "<Description>";
    private String myDefaultImage = "images/square.png";
    private double myDefaultWidth = 100;
    private double myDefaultHeight = 100;

    public ProfileSFC () {
        this.myView = new ProfileSFV();
    }

    @Override
    public void initializeFields () {
        myView.populateWithData(myDefaultName, myDefaultDescription, myDefaultImage, myDefaultWidth,
                                myDefaultHeight);
    }

    @Override
    public void updateItem (T item) {
        String name = myView.getName();
        String desc = myView.getDescription();
        String url = myView.getImage();
        double width = myView.getMyImageWidth();
        double height = myView.getMyImageHeight();
        item.getProfile().setNew(name, desc, url, width, height);

    }

    @Override
    public void populateViewsWithData (T item) {
        IProfile profile = item.getProfile();
        myView.populateWithData(profile.getName().get(), profile.getDescription().get(),
                                profile.getImageURL(), profile.getImageWidth().get(),
                                profile.getImageHeight().get());
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
