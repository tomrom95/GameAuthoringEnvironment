package gameauthoring.creation.subforms;

import engine.profile.IProfilable;
import gameauthoring.creation.entryviews.IFormDataManager;


public class ProfileSubFormController<T extends IProfilable> implements ISubFormController<T> {

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
    public void updateItem (T item) {
        String name = myFormData.getValueProperty(myView.getMyNameKey()).get();
        String desc = myFormData.getValueProperty(myView.getMyDescriptionKey()).get();
        String url = myFormData.getValueProperty(myView.getMyImageKey()).get();
        double width =
                Double.parseDouble(myFormData.getValueProperty(myView.getMyImageWidthKey()).get());
        double height =
                Double.parseDouble(myFormData.getValueProperty(myView.getMyImageHeightKey()).get());
        item.getProfile().setNew(name, desc, url, width, height);

    }

    @Override
    public void populateViewsWithData (T item) {
        myFormData.set(myView.getMyNameKey(), item.getProfile().getName().get());
        myFormData.set(myView.getMyDescriptionKey(), item.getProfile().getDescription().get());
        myFormData.set(myView.getMyImageKey(), item.getProfile().getImageURL());
        myFormData.set(myView.getMyImageWidthKey(),
                       Double.toString(item.getProfile().getImageWidth().doubleValue()));
        myFormData.set(myView.getMyImageHeightKey(),
                       Double.toString(item.getProfile().getImageHeight().doubleValue()));
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
