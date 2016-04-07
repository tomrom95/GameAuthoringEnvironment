package gameauthoring.characters;

/**
 * This interface specifies the methods each SubFormView must implement to allow
 * its SubFormController to control it
 * 
 * @author Jeremy Schreck
 *
 */
public interface ISubFormView {

    /**
     * Get the data entered in the SubFormView
     * 
     * @return A FormDataManager object that holds the data
     */
    FormDataManager getData ();

    /**
     * Populate the SubFormView's fields with new data
     * 
     * @param data A FormDataManager object containing the new data
     */
    void populateWithData (FormDataManager data);
}
