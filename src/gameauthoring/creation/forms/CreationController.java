package gameauthoring.creation.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.ProfileSFC;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.ObservableList;


/**
 * This class is the high level controller for a creation form/list view
 * 
 * 
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <T> The type of object to be created and stored -- ex: Sprite, Attribute, Group
 */
public abstract class CreationController<T extends IProfilable> {
    private IObjectCreationView<T> myView;
    private List<? extends ISubFormController<T>> mySubFormControllers;
    private String myTitle;
    private SubFormControllerFactory<T> mySFCFactory;
    private DefinitionCollection<T> myDefinitionCollection;

    // New Design stuff
    private List<String> mySubFormTemplate;
    private Map<T, List<? extends ISubFormController<T>>> myMap;

    /**
     * Constructor
     * 
     * Note: subFormStrings moved to init, but kept here for now in case we want to
     * change back
     * 
     * @param title The creation controller's title
     * @param subFormStrings
     * @param authorshipData Data shared by various authorship elements (listsof created items)
     */
    public CreationController (String title,
                               List<String> subFormStrings,
                               IGame myGame) {

        myTitle = title;
        myView = new ObjectCreationView<T>();
        setMySFCFactory(createSFCFactory(myGame));

        myDefinitionCollection = new DefinitionCollection<>(getMyTitle(),
                                                            getMyObjectCreationView().getItems());
        mySubFormTemplate = subFormStrings;
        myMap = new HashMap<T, List<? extends ISubFormController<T>>>();
        addToAuthorshipData(myGame.getAuthorshipData());

    }

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
     * @param subFormStrings The strings from xml representing which subforms to create
     */
    public void init (List<String> subFormStrings) {
        mySubFormControllers =
                getMySFCFactory()
                        .createSubFormControllers(subFormStrings);
        List<ISubFormView> subFormViews = getSubFormViews(getMySubFormControllers());
        myView.init(subFormViews);
        setupConnections();
        newItem();

    }

    /**
     * Hook up observable list of items to authorship data so other views can have access
     * 
     * @param authorshipData
     */
    protected abstract void addToAuthorshipData (AuthorshipData authorshipData);

    /**
     * Set up event handler connections
     */
    private void setupConnections () {
        IFormView formView = getMyObjectCreationView().getFormView();
        formView.setSaveAction(e -> saveItem());
        formView.setDeleteAction(e -> deleteItem());
        formView.setNewAction(e -> newItem());

        IObjectCreationView<T> creationView = getMyObjectCreationView();
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

        // subFormViews.add(getMyProfileSubFormController().getSubFormView());
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
        // getMyProfileSubFormController().updateItem(getMyCurrentItem());
        for (ISubFormController<T> subFormController : getMySubFormControllers()) {
            subFormController.updateItem(getMyCurrentItem()); // make more generic later
        }
        this.getMyObjectCreationView().getObjectListView().refreshItems();
    }

    /**
     * Delete the given item
     *
     * @param item the item to delete
     */
    private void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
        showAndEdit(getMyCurrentItem());
    }

    /**
     * Method handler when user clicks "new" object
     * 
     */
    private void newItem () {
        T item = createBlankItem();
        addItem(item);
        getMyObjectCreationView().getObjectListView().setSelectedItem(item);
        showAndEdit(item);

        // New Design
        // List<? extends ISubFormController<T>> SFCs =
        // getMySFCFactory().createSubFormControllers(this.mySubFormTemplate);
        // myMap.put(item, SFCs);

        // showAndEdit(item);

        // initializeSubFormViews();
    }

    /**
     * Initializes the subformviews with default data
     */
    private void initializeSubFormViews () {
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
        if (getMyCurrentItem() != null) {
            for (ISubFormController<T> subFormController : getMySubFormControllers()) {
                subFormController.populateViewsWithData(getMyCurrentItem());
            }
        }
        // New Design
        // this.mySubFormControllers = myMap.get(item);
        // this.myView.getFormView().setViews(getSubFormViews(mySubFormControllers));

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

    public IObjectCreationView<T> getMyObjectCreationView () {
        return myView;
    }

    private List<? extends ISubFormController<T>> getMySubFormControllers () {
        return mySubFormControllers;
    }

    private T getMyCurrentItem () {
        return this.myView.getCurrentItem();
    }

    public String getMyTitle () {
        return myTitle;
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
        getMyObjectCreationView().getObjectListView().setMyItems(col.getItems());
        // setMyTitle(col.getTitle());
    }

}
