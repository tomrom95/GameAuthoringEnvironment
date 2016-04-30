package gameauthoring.creation.entryviews;

import javafx.scene.control.TextField;


/**
 * TextField that only accepts one character
 * 
 * @author Joe Lilien
 *
 */

public class CharacterTextField extends TextField {

    @Override
    public void replaceText (int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection (String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate (String text) {
        return text.length() <= 1;
    }
}
