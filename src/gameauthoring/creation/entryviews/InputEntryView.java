package gameauthoring.creation.entryviews;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Abstract class that defines an EntryView which can take in some form of text input, current
 * subclasses include a text input EntryView and a number input EntryView that only allows users to
 * type in numbers
 * 
 * @author Joe Lilien
 *
 */
public abstract class InputEntryView extends EntryView {

    private HBox myContainer;
    private TextInputControl myTextInput;
    private double width;
    private double height;

    public InputEntryView (String label,
                           double width,
                           double height,
                           String cssClass) {
        super(label);
        this.width = width;
        this.height = height;
    }

    @Override
    protected void init (String label, String cssClass) {
        myTextInput.setMinSize(width, height);
        myTextInput.setMaxSize(width, height);
        myContainer = getMyFactory().makeHBox(20, Pos.CENTER, getLabel(), myTextInput);
        myContainer.getStyleClass().add(cssClass);
    }

    protected void setInputControl (TextInputControl text) {
        this.myTextInput = text;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
