package gameauthoring.characters;

import gameauthoring.Glyph;
import gameauthoring.IObjectListView;


/**
 * Interface for a view object that lays out an IFormView and an IObjectListView
 * 
 * Note: setters might not be necessary (they will probably be passed in to constructor
 * instead), but for now I include them to specify what an IObjectCreationView
 * will be structured
 * 
 * @author Jeremy Schreck
 *
 */
public interface IObjectCreationView extends Glyph {

    /**
     * Get the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @return The IObjectListView object
     */
    IObjectListView getObjectListView ();

    /**
     * Get the IFormView, which is the view containing the form to create a new object
     * 
     * 
     * @return The IFormView object
     */
    IFormView getFormView ();

    /**
     * Set the IObjectListView, which is the view containing the list of objects
     * 
     * 
     * @param objectListView The IObjectListView object
     */
    void setObjectListView (IObjectListView objectListView);

    /**
     * Set the IFormView, which is the view containing the form to create a new object
     * 
     * 
     * @param formView The IFormView object
     */
    void setFormView (IFormView formView);

    // ICreationController<?> getCreationController (); controller has view instead
}
