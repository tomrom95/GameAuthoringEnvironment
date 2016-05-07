// This entire file is part of my masterpiece.
// Jeremy Schreck
/*
 * Here's why it's good design:
 * 
 * It demonstrates the use of an abstraction. When designing the authoring 
 * environment, we realized a common theme was creating some type of object, 
 * such as sprites, attributes, event packages, groups etc. Each of these could
 * involve some type of form that you fill out to create the item, a view 
 * displaying the list of items you have created, and the ability to save, 
 * delete, edit and create new items. We abstracted much of this design into 
 * common superclasses such as this one to be able to reuse as much code as 
 * possible.
 *
 * I use factories and reflection to dynamically instantiate the creation controller
 * classes and the subforms they use based on resource files (explained further
 * in the CreationControllerFactory class and related resource files).
 * 
 * I use generics to be able to re-use the same code across different types of 
 * objects. This class can be used to control the creation of any type of object. 
 * For example,we use it in our project for the creation of SpriteDefinitions, 
 * AttributeDefinitions, SpriteGroups, and more, all of which are classes in 
 * separate hierarchies.
 * 
 * The logic is contained in the controller and not the view. The view just
 * knows how to draw itself, and gets passed methods to call when certain
 * events happen (such as save, edit, new, delete). These methods are implemented
 * in this controller class. 
 * 
 * I use lambdas to pass methods to the views as "Runnables" that they should call
 * when certain user events occur.
 * 
 * I use Java streams to make the syntax cleaner, demonstrating my knowledge
 * of advanced Java techniques.
 * 
 * I use the Template Method with abstract methods to prevent duplicated code
 * in the subclasses.
 * 
 * The title of the creation controller is retrieved through a resource file,
 * allowing it to be customized without changing any code and allowing the support
 * of many languages.
 * 
 * Last but not least, all the instance variables are private!
 * 
 */

package gameauthoring.creation.forms;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.AuthorshipData;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.ListWrapper;
import javafx.collections.ObservableList;
import splash.LocaleManager;


/**
 * This is an abstract class for an object that controls the creation of items in the authoring
 * environment. It is associated with a corresponding CreationView class.
 *
 * @author Jeremy Schreck, Joe Lilien
 *
 * @param <T> The type of item to be created and stored -- ex: SpriteDefinition,
 *        AttributeDefinition, SpriteGroup, etc
 */
public abstract class CreationController<T extends IProfilable> {
    private ResourceBundle myLanguages = ResourceBundle.getBundle("languages/labels",
                                                                  LocaleManager.getInstance()
                                                                          .getCurrentLocaleProperty()
                                                                          .get());
    private String myKey;
    private ICreationView<T> myCreationView;
    private List<? extends ISubFormController<T>> mySubformControllers;
    private ListWrapper<T> myItemsWrapper;
    private AuthorshipData myAuthorshipData;

    /**
     * Constructs a CreationController
     *
     * @param key The creation controller's key which is used to get its title from a resource file
     * @param subformStrings List of String Ids specifying which subforms to create
     * @param game The current game object
     */
    public CreationController (String key,
                               List<String> subformStrings,
                               IGame game) {

        myKey = key;
        myAuthorshipData = game.getAuthorshipData();
        setMyItemsWrapper(getItemsWrapperFromAuthorshipData(game.getAuthorshipData()));
        SubFormControllerFactory<T> sfcFactory = createSFCFactory(game);
        setMySubFormControllers(sfcFactory.createSubFormControllers(subformStrings, key));
        List<ISubFormView> subFormViews = getSubFormViews(getMySubFormControllers());
        myCreationView = new CreationView<T>(getMyItems(), subFormViews);
        setupConnections();

    }

    /**
     * Gets the corresponding list of items in authorship data to be used by this creation
     * controller
     *
     * @return The list wrapper containing the list of items
     */
    protected abstract ListWrapper<T> getItemsWrapperFromAuthorshipData (AuthorshipData authorshipData);

    /**
     * Subclasses specify which SFC factory to use to create the subforms
     *
     * @param game The current game object
     * @return The SFC factory class to use to instantiate SubFormControllers
     */
    protected abstract SubFormControllerFactory<T> createSFCFactory (IGame game);

    /**
     * Set up event handler connections
     */
    private void setupConnections () {
        getMyCreationView().setSaveAction( () -> saveItem());
        getMyCreationView().setDeleteAction( () -> deleteItem());
        getMyCreationView().setNewAction( () -> newItem());
        getMyCreationView().setEditAction( () -> editItem());
    }

    /**
     * Retrieve a list of SubformViews from the list of SubformControllers
     *
     * @param subformControllers The SubformControllers
     * @return The list of SubformViews
     */
    private List<ISubFormView> getSubFormViews (List<? extends ISubFormController<T>> subformControllers) {
        return subformControllers.stream().map(e -> e.getSubFormView())
                .collect(Collectors.toList());
    }

    /**
     * Save the item currently being edited in the form
     */
    private void saveItem () {
        getMySubFormControllers().forEach(e -> e.updateItem(getMyCurrentItem()));
    }

    /**
     * Edit the currently selected item
     * 
     * Note: the currently selected item is null if no items are in the list
     */
    private void editItem () {
        if (getMyCurrentItem() != null) {
            getMySubFormControllers().forEach(sfc -> sfc.populateViewsWithData(getMyCurrentItem()));
        }

    }

    /**
     * Delete the currently selected item
     */
    protected void deleteItem () {
        getMyItems().remove(getMyCurrentItem());
    }

    /**
     * Create a new item and change display accordingly
     */
    private void newItem () {
        T item = createBlankItem();
        addItem(item);
        getMyCreationView().setSelectedItem(item);
        populateViewsWithDefaults();
        saveItem();

    }

    /**
     * Tells each SubformView to populate itself with default data
     */
    private void populateViewsWithDefaults () {
        getMySubFormControllers().forEach(sfc -> sfc.initializeFields());
    }

    /**
     * Method to be overwritten by subclasses that creates a new instance of type T
     *
     * @return The newly created item
     */
    protected abstract T createBlankItem ();

    /**
     * Add the given item to the list of available items in the model
     *
     * @param item The item to add
     */
    private void addItem (T item) {
        getMyItems().add(item);
    }

    // Getters and setters

    public ICreationView<T> getMyCreationView () {
        return myCreationView;
    }

    protected T getMyCurrentItem () {
        return this.myCreationView.getCurrentItem();
    }

    public String getMyTitle () {
        return getMyLanguageBundle().getString(getMyTitleKey());
    }

    protected AuthorshipData getMyAuthorshipData () {
        return myAuthorshipData;
    }

    protected String getMyKey () {
        return myKey;
    }

    private String getMyTitleKey () {
        return getMyItemsWrapper().getListMetadata().getTitleKey();
    }

    private List<? extends ISubFormController<T>> getMySubFormControllers () {
        return mySubformControllers;
    }

    private void setMySubFormControllers (List<? extends ISubFormController<T>> mySubFormControllers) {
        this.mySubformControllers = mySubFormControllers;
    }

    private ObservableList<T> getMyItems () {
        return getMyItemsWrapper().getItems();
    }

    private ListWrapper<T> getMyItemsWrapper () {
        return myItemsWrapper;
    }

    private void setMyItemsWrapper (ListWrapper<T> col) {
        this.myItemsWrapper = col;
    }

    private ResourceBundle getMyLanguageBundle () {
        return myLanguages;
    }

}
