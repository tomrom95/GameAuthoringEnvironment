package gameauthoring.creation.entryviews;

import javafx.scene.Node;
import javafx.scene.control.TextInputControl;


/**
 * Abstract class that defines an EntryView which can take in some form of text input, current
 * subclasses include a text input EntryView and a number input EntryView that only allows users to
 * type in numbers
 *
 * @author Joe Lilien
 *
 */
public abstract class InputEntryView extends EntryView {

    private TextInputControl myTextInput;
    private double myWidth;
    private double myHeight;

    public InputEntryView (String label,
                           double width,
                           double height,
                           String cssClass) {
        super(label, cssClass);
        myWidth = width;
        myHeight = height;
    }

    @Override
    protected void init () {
        myTextInput.setMinSize(myWidth, myHeight);
        myTextInput.setMaxSize(myWidth, myHeight);
        getMyContainer().getChildren().add(myTextInput);
    }

    protected void setInputControl (TextInputControl text) {
        myTextInput = text;
    }

    @Override
    public Node draw () {
        return getMyContainer();
    }

}
