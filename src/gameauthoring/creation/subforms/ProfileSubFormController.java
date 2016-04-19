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
    private String myDefaultName = "<Name>";
    private String myDefaultDescription = "<Description>";
    private String myDefaultImage = "images/Square.png";
            

    public ProfileSubFormController () {
        this.myView = new ProfileSubFormView();
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultName, myDefaultDescription, myDefaultImage);
        
    }
    private void populateViewsWithData(String name, String desc, String url){
        myFormData.set(myView.getMyNameKey(), name);
        myFormData.set(myView.getMyDescriptionKey(), desc);
        myFormData.set(myView.getMyImageKey(), url);
    }
    @Override
    public void updateItem (T item) {
        String name = myFormData.getValueProperty(myView.getMyNameKey()).get();
        String desc = myFormData.getValueProperty(myView.getMyDescriptionKey()).get();
        String url = myFormData.getValueProperty(myView.getMyImageKey()).get();

        item.getProfile().setNew(name, desc, url);

    }

    @Override
    public void populateViewsWithData (T item) {
        String name =  item.getProfile().getName().get();
        String desc = item.getProfile().getDescription().get();
        String url = item.getProfile().getImageURL();
        populateViewsWithData(name, desc, url);
    }
    

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

  

}
