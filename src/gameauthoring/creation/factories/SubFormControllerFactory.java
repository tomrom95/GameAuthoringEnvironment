package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.List;
import engine.AuthorshipData;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ProfileSFC;
import gameauthoring.util.ErrorMessage;



public abstract class SubFormControllerFactory<T extends IProfilable> {

    private IGame myGame;

    public SubFormControllerFactory (IGame game) {
        myGame = game;
    }

    public List<ISubFormController<T>> createSubFormControllers (List<String> subFormStrings, String ccKey) {
        List<ISubFormController<T>> list = new ArrayList<>();
        for (String subFormString : subFormStrings) {
            if(subFormString.equals("Profile")){
                list.add(createProfileSFC(ccKey));
            }else{
                list.add(createSFCAndHandleErrors(subFormString));
            }
        }
        return list;

    }
    
    /**
     * Method for creating an SFC based on a string ID for that SFC
     * 
     * @param type A string indicating which SFC to instantiate
     * @param params Optional params for the sfc's constructor
     * @return The ISubFormController that the factory creates
     * @throws ReflectionException
     * @throws ClassCastException
     */
    protected abstract ISubFormController<T> createSubFormController (String type, Object ... params) throws ReflectionException, ClassCastException;

    private ISubFormController<T> createSFCAndHandleErrors(String type){
        String errorMsg = String.format("Error creating subform of type %s.\nCheck properties files.", type);
        try {
            return createSubFormController(type);
        } catch (ReflectionException e) {
            String message = String.format("%s Error was a Reflection Exception: %s", errorMsg, e.getMessage());
            throw new ReflectionException(message);
        } catch (ClassCastException e){
            String message = String.format("%s Error was a ClassCast Exception: %s", errorMsg, e.getMessage());
            throw new ClassCastException(message);
        }
    }

    protected IGame getMyGame () {
        return myGame;
    }

    public AuthorshipData getMyAuthorshipData () {
        return getMyGame().getAuthorshipData();
    }

    public ProfileSFC<T> createProfileSFC (String key) {
        return new ProfileSFC<T>(key);
    }

}
