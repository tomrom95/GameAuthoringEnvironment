package gameauthoring.characters;

import java.util.ArrayList;

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
 * @author Joe Lilien
 * 
 */
public abstract class EntryView implements IEntryView {

    private FormData myFormData;
    private String myLabel;
 
    public EntryView(String label, IFormDataManager data){
        this.myLabel = label;
        this.myFormData = new FormData(label, new ArrayList<String>());
        data.add(myFormData);
    }
    
    public String getLabel(){
        return myLabel;
    }
    
    @Override
    public FormData getData () {
        return myFormData;
    }

    @Override
    public void populateWithData (FormData data) {
        this.myFormData = data;

    }

}
