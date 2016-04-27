package gameauthoring.creation.forms;

import java.util.*;
import engine.AuthorshipData;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.ObservableList;
import splash.LocaleManager;


/**
 * This class is the high level controller for a creation form/list view
 * 
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <T> The type of object to be created and stored -- ex: Sprite, Attribute, Group
 */
public abstract class CreationController<T extends IProfilable> {
    private ICreationView<T> myView;
    private List<? extends ISubFormController<T>> mySubFormControllers;
    private String myKey;
    private SubFormControllerFactory<T> mySFCFactory;
    private DefinitionCollection<T> myDefinitionCollection;
    private ResourceBundle myResources;

    private static String RESOURCE_PATH = "languages/labels";

    /**
     * Constructor
     * 
     * Note: subFormStrings moved to init, but kept here for now in case we want to
     * change back
     * 
     * @param key The creation controller's key which is used to get its title from a resource file
     * @param subFormStrings
     * @param authorshipData Data shared by various authorship elements (listsof created items)
     */
    public CreationController (String key,
                               List<String> subFormStrings,
                               IGame myGame) {

        myResources =
                ResourceBundle
                        .getBundle(RESOURCE_PATH,
                                   LocaleManager.getInstance().getCurrentLocaleProperty().get());
        myKey = key;
        myView = new CreationView<T>();
        setMySFCFactory(createSFCFactory(myGame));
        setMyDefinitionCollection(getDefinitionCollectionFromAuthorshipData(myGame
                .getAuthorshipData()));
        init(subFormStrings);

    }

    /**
     * Gets the corresponding definition collection in authorship data to connect the creation
     * controller
     * 
     * @return The definition collection
     */
    protected abstract DefinitionCollection<T> getDefinitionCollectionFromAuthorshipData (AuthorshipData authorshipData);

    /**
     * Subclasses specify which SFC factory to use to create the sub forms
     * 
     * @param game The current game object
     * @return The SFC factory class to use to instantiate SFCs
     */
    protected abstract SubFormControllerFactory<T> createSFCFactory (IGame game);

    /**
     * Initialization. Creates its subFormControllers from a factory, passes
     * the subformviews to init its creationview, and sets up up action connections
     * like save, new, edit, delete
     * 
     * Note: this method must be called before use of full functionality CreationController
     * 
     * Note: did this to erase dependency between AttributeCreationController
     * needing to be created before SelectAttribute subformcontroller
     * 
     * Update: I think dependency is gone now that lists are lazily instantiated in AuthorshipData
     * 
     * @param subFormStrings The strings from xml representing which subforms to create
     */
    public void init (List<String> subFormStrings) {
        mySubFormControllers = getMySFCFactory().createSubFormControllers(subFormStrings);
        List<ISubFormView> subFormViews = getSubFormViews(getMySubFormControllers());
        myView.init(subFormViews);
        setupConnections();
    }

    /**
     * Set up event handler connections
     */
    private void setupConnections () {
        IFormView formView = getMyObjectCreationView().getFormView();
        formView.setSaveAction(e -> saveItem());
        formView.setDeleteAction(e -> deleteItem());
        formView.setNewAction(e -> newItem());

        ICreationView<T> creationView = getMyObjectCreationView();
        creationView.setEditAction(e -> showAndEdit(e));
    }

    /**
     * Generate list of subformviews from the list of subformcontrollers
     * 
     * @param subFormControllers The subformcontrollers
     * @return The list of subformviews
     */
    private List<ISubFormView> getSubFormViews (List<? extends ISubFormController<T>> subFormControllers) {
        List<ISubFormView> subFormViews = new ArrayList<ISubFormView>();

        for (ISubFormController<T> subFormController : subFormControllers) {
            subFormViews.add(subFormController.getSubFormView());
        }
        return subFormViews;
    }

    /**
     * Save the item currently being edited in the form
     * 
     */
    private void saveItem () {
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.updateItem(getMyCurrentItem());
        }
        
        this.getMyObjectCreationView().getCreationListView().refreshItems(); 
    }

    /**
     * Delete the given item
     *
     * @param item the item to delete
     */
    private void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
        if (getMyItems().isEmpty()) {
            populateViewsWithDefaults();
            getMyObjectCreationView().getFormView().hideForm();

        }
        else {
            showAndEdit(getMyCurrentItem());
            getMyObjectCreationView().getFormView().showForm();

        }
    }

    /**
     * Method handler when user clicks "new" object
     * 
     */
    private void newItem () {
        T item = createBlankItem();
        addItem(item);
        getMyObjectCreationView().getCreationListView().setSelectedItem(item);

        getMyObjectCreationView().getFormView().showForm();

        showAndEdit(item);// or populateViewsWithDefaults, depending on where defaults are

        // populateViewsWithDefaults();
    }

    /**
     * Populates each subformview with default data
     */
    private void populateViewsWithDefaults () {
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.initializeFields();
        }
    }

    /**
     * Method to be overwritten by subclasses that creates a blank object of type T
     * 
     * @return The item
     */
    protected abstract T createBlankItem ();

    /**
     * Method called when user clicks a cell in the list view
     * 
     * Note: The item parameter is no longer completely necessary, as we are always
     * using getMyCurrentItem(), which is always set to the listview's current
     * selected item
     * 
     * @param item The item contained in the cell that was clicked
     */
    private void showAndEdit (T item) {
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.populateViewsWithData(getMyCurrentItem());
        }

    }

    /**
     * Add the given item to the list of available items in the model
     * 
     * @param item The item to add
     */
    private void addItem (T item) {
        getMyItems().add(item);
    }

    // Getters and setters
    private ObservableList<T> getMyItems () {
        return getMyObjectCreationView().getItems();
    }

    public ICreationView<T> getMyObjectCreationView () {
        return myView;
    }

    private List<? extends ISubFormController<T>> getMySubFormControllers () {
        return mySubFormControllers;
    }

    private T getMyCurrentItem () {
        return this.myView.getCurrentItem();
    }

    public String getMyTitle () {
        return getMyResources().getString(getMyKey());
    }

    protected String getMyKey () {
        return myKey;
    }

    public SubFormControllerFactory<T> getMySFCFactory () {
        return mySFCFactory;
    }

    public void setMySFCFactory (SubFormControllerFactory<T> sfcFactory) {
        this.mySFCFactory = sfcFactory;
    }

    protected void setMySubFormControllers (List<? extends ISubFormController<T>> mySubFormControllers) {
        this.mySubFormControllers = mySubFormControllers;
    }

    protected DefinitionCollection<T> getMyDefinitionCollection () {
        return myDefinitionCollection;
    }

    protected void setMyDefinitionCollection (DefinitionCollection<T> col) {
        this.myDefinitionCollection = col;
        System.out.println(col.getItems());
        getMyObjectCreationView().getCreationListView().setMyItems(col.getItems());
    }

    protected ResourceBundle getMyResources () {
        return myResources;
    }

}
