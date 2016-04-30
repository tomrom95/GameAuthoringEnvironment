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
 * This class is the controller for a CreationView containing a FormView and CreationListView
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <T> The type of object to be created and stored -- ex: SpriteDefinition,
 *        AttributeDefinition, Group
 */
public abstract class CreationController<T extends IProfilable> {
    private ICreationView<T> myView;
    private List<? extends ISubFormController<T>> mySubFormControllers;
    private String myKey;
    private SubFormControllerFactory<T> mySFCFactory;
    private DefinitionCollection<T> myDefinitionCollection;
    private AuthorshipData myData;
    private ResourceBundle myResources = ResourceBundle.getBundle("languages/labels",
                                                                  LocaleManager.getInstance()
                                                                          .getCurrentLocaleProperty()
                                                                          .get());

    /**
     * Constructs a CreationController
     * 
     * @param key The creation controller's key which is used to get its title from a resource file
     * @param subFormStrings Strings specifying which subforms to create
     * @param game The current game object
     */
    public CreationController (String key,
                               List<String> subFormStrings,
                               IGame game) {

        myKey = key;
        myView = new CreationView<T>();
        myData = game.getAuthorshipData();
        setMySFCFactory(createSFCFactory(game));
        setMyDefinitionCollection(getDefinitionCollectionFromAuthorshipData(game
                .getAuthorshipData()));
        setMySubFormControllers(getMySFCFactory().createSubFormControllers(subFormStrings, key));
        List<ISubFormView> subFormViews = getSubFormViews(getMySubFormControllers());
        myView.init(subFormViews);
        setupConnections();

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
     * @return The SFC factory class to use to instantiate SubFormControllers
     */
    protected abstract SubFormControllerFactory<T> createSFCFactory (IGame game);

    /**
     * Set up event handler connections
     */
    private void setupConnections () {
        IFormView formView = getMyCreationView().getFormView();
        formView.setSaveAction( () -> saveItem());
        formView.setDeleteAction( () -> deleteItem());
        formView.setNewAction( () -> newItem());

        ICreationView<T> creationView = getMyCreationView();
        creationView.setEditAction( () -> showAndEdit());
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

    }

    /**
     * Delete the given item
     *
     * @param item the item to delete
     */
    private void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
        if (getMyItems().isEmpty()) {
            getMyCreationView().getFormView().hideForm();
        }
        else {
            showAndEdit();
            getMyCreationView().getFormView().showForm();

        }
    }

    /**
     * Method handler when user clicks "new" object
     * 
     */
    protected T newItem () {
        T item = createBlankItem();
        addItem(item);
        getMyCreationView().getCreationListView().setSelectedItem(item);
        populateViewsWithDefaults();
        getMyCreationView().getFormView().showForm();
        populateViewsWithDefaults();
        saveItem();
        return item;
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
     * Method called when user clicks a cell in the list view to edit that item
     * 
     * @param item The item contained in the cell that was clicked
     */
    private void showAndEdit () {
        if (getMyCurrentItem() != null) {
            for (ISubFormController<T> subFormController : getMySubFormControllers()) {
                subFormController.populateViewsWithData(getMyCurrentItem());
            }
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
        return getMyCreationView().getItems();
    }

    public ICreationView<T> getMyCreationView () {
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

    private void setMySubFormControllers (List<? extends ISubFormController<T>> mySubFormControllers) {
        this.mySubFormControllers = mySubFormControllers;
    }

    protected DefinitionCollection<T> getMyDefinitionCollection () {
        return myDefinitionCollection;
    }

    protected void setMyDefinitionCollection (DefinitionCollection<T> col) {
        this.myDefinitionCollection = col;
        getMyCreationView().getCreationListView().setMyItems(col.getItems());
    }

    protected ResourceBundle getMyResources () {
        return myResources;
    }

    protected AuthorshipData getMyData () {
        return myData;
    }

}
