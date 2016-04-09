package gameauthoring.characters;

/**
 * Abstract class for an simple implementation of IEntryView
 * 
 * Note: one option is to have all of our entry views subclass this, and
 * in populateWithData they will call super and then access their myFormData
 * 
 * Other option is to never store formData and just regenerate a FormData object
 * in getData() every time, and use the passed in form data in populateWithData
 * 
 * design decision: state vs functional programming
 * 
 * @author Jeremy Schreck
 *
 */
public abstract class EntryView implements IEntryView {

    private FormData myFormData;

    /*public EntryView (FormData formData) {
        myFormData = formData;
    }*/

    @Override
    public FormData getData () {
        return myFormData;
    }

    @Override
    public void populateWithData (FormData data) {
        this.myFormData = data;

    }

}
