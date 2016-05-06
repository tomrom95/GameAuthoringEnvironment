// This entire file is part of my masterpiece.
// Joseph Lilien

/**
 * This class is an abstract class that serves to define much of the functionality and create a
 * template structure for Sub Form Controllers that control and manage one or more simpler SFCs.
 * This functionality is absolutely necessary for our Authoring Environment to support as it lends a
 * great deal of new flexibility to the user in creating a game by allowing to fully capitalize on
 * the extensible structure of the backend classes. For example, a sprite definition in the backend
 * may be constructed with any (not preset) number of module definitions that can completely
 * redefine its functionality. A specific instance of this is that a sprite can be given multiple
 * firing modules to allow it to fire completely different weapons at completely different times.
 * 
 * One of the most difficult problems to solve in working out the implementation and design for this
 * feature was determining the best way in which to allow for both flexibility for future extension
 * (should the user decide to add new firing module definitions for example) while still allowing us
 * to have enough information to view an existing sprite definition and repopulate the authoring
 * view according some arbitrary amount of information.
 * 
 * The currently refactored version of this code allows for all the necessary functionality of both
 * data storage and repopulation, maintains complete separation of view (JavaFX) and data components
 * (takes into constructor a view that implements the interface required for functionality
 * (IMultiSFView), and uses high level Java techniques such as Generic class definition (specified
 * to extend IProfilable) and method definition/passing via lambdas (to maintain view and model
 * separation).The structure and template methods it provides allows for easy future extension by
 * subclassing without the need for any duplicate code (see FiringSFC vs EventSFC vs EffectSFC).
 * 
 * Most importantly, this feature is completely extensible and satisfies the open closed principle.
 * This is thanks to the use of factory classes, properties files, and reflection in the generation
 * and management of the simpler SFCs contained within each subclass. For example, if a developer in
 * the future wanted to add a completely new type of module to allow the user to choose from, they
 * would simply have to create the necessary classes to represent its data in the GUI (associated
 * SFV and SFC) and add a single line to the associated properties file. Absolutely no existing code
 * would have to be altered.
 * 
 * 
 * INCLUDED IN MASTERPIECE:
 * 
 * - This file (MultiSFController)
 * - A sample SubClass that would extend this class (Firing SFC)
 * - A sample Factory class that would be included with that subclass (FiringSFCFactory)
 * - The necessary Properties files to achieve reflection functionality (dynamic_sfc_contents,
 *   sfc_classpath)
 * 
 * 
 */
package gameauthoring.creation.subforms.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.factories.MultiOptionFactory;
import gameauthoring.creation.subforms.IMultiSFView;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.fire.RemovableSFC;


/**
 * Abstract class to define functionality and create template structure for classes that must manage
 * and control one or more simpler SFCs of a given type
 * 
 * @author Joseph Lilien
 *
 * @param <T> profilable item that SFCs under this hierarchy access and edit
 */
public abstract class MultiSFController<T extends IProfilable> implements ISubFormController<T> {

    private IGame myGame;
    private IMultiSFView myView;
    private MultiOptionFactory<T> mySFCFactory;
    private List<RemovableSFC<T>> mySFCs = new ArrayList<>();
    private ResourceBundle myOptionsFile =
            ResourceBundle.getBundle("defaults/dynamic_sfc_contents");
    private ResourceBundle myDefClasspaths = ResourceBundle.getBundle("defaults/sfc_classpath");
    private List<String> myOptions;

    /**
     * Constructor for this class, initializes necessary fields according to arguments
     * 
     * @param view: The desired view to represent this data in a GUI
     * @param game: The current game object
     */
    public MultiSFController (IMultiSFView view, IGame game) {
        myGame = game;
        myView = view;
        setMySFCFactory(new MultiOptionFactory<T>(game));
    }

    /**
     * Sets the action of each component (regardless of object type) in the associated view's list
     * of components to generate a new SFC/SFV pair of the desired type, according the value/key
     * pairs defined in the properties file
     */
    protected void setViewActions () {
        for (int i = 0; i < getMyView().getMyViewComponents().size(); i++) {
            String sfcID = getMyOptions().get(i);
            RemovableSFC<T> sfc = getMySFCFactory().createSubFormController(getMyDefClasspaths()
                    .getString(sfcID), getMyGame(), this);
            getMyView().setViewComponentAction(getMyView().getMyViewComponents().get(i),
                                               e -> addSFC(sfc));
        }
    }

    protected void addSFC (RemovableSFC<T> sfc) {
        getMySFCs().add(sfc);
        getMyView().addOrSetSFV(sfc.getSubFormView());
    }

    protected void clearSFCs () {
        getMySFCs().clear();
        getMyView().clearSFVs();
    }

    /**
     * Resets associated (determined by subclass) contents of currently edited item of type T to be
     * overwritten by information defined by the user in the GUI
     * 
     * @param item: current Item to be edited by SFCs
     */
    protected abstract void resetContents (T item);

    /**
     * Returns a list of one or more objects contained within item that are to be edited or polled
     * to populate the view with data.
     * 
     * @param item: current item to be edited
     * @return: List of objects of to be edited ... (? extends Object) to work with reflection in
     *          factory
     */
    protected abstract List<? extends Object> getListofEditables (T item);

    /**
     * Removes sfc from list of sfc and associated editable object from item of type T
     * 
     * @param sfc: SFC to be removed
     */
    public void removeSFC (RemovableSFC<T> sfc) {
        getMySFCs().remove(sfc);
        getMyView().removeSFV(sfc.getSubFormView());
        if (sfc.getModuleDefinition() != null) {
            sfc.removeModule(sfc.getModuleDefinition());
        }
    }

    @Override
    public void populateViewsWithData (T item) {
        clearSFCs();
        List<? extends Object> objects = getListofEditables(item);
        for (Object object : objects) {
            RemovableSFC<T> sfc =
                    getMySFCFactory().createSubFormController(object.getClass().getName(),
                                                              getMyGame(), this, object);
            sfc.populateViewsWithData(item);
            addSFC(sfc);
        }
    }

    @Override
    public void updateItem (T item) {
        resetContents(item);
        getMySFCs().forEach(e -> e.updateItem(item));
    }

    @Override
    public ISubFormView getSubFormView () {
        return getMyView();
    }

    protected List<String> getMyOptions () {
        return myOptions;
    }

    protected void setMyOptions (List<String> options) {
        myOptions = options;
    }

    protected ResourceBundle getMyOptionsFile () {
        return myOptionsFile;
    }

    protected void setMyView (IMultiSFView view) {
        this.myView = view;
    }

    private ResourceBundle getMyDefClasspaths () {
        return myDefClasspaths;
    }

    private IGame getMyGame () {
        return myGame;
    }

    private List<RemovableSFC<T>> getMySFCs () {
        return mySFCs;
    }

    private IMultiSFView getMyView () {
        return myView;
    }

    private MultiOptionFactory<T> getMySFCFactory () {
        return mySFCFactory;
    }

    private void setMySFCFactory (MultiOptionFactory<T> sfcFactory) {
        this.mySFCFactory = sfcFactory;
    }

}
