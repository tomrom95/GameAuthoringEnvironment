package gameauthoring.creation.entryviews;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;


/**
 * Abstract class that defines an EntryView which can take in some form of text input, current
 * subclasses include a text input EntryView and a number input EntryView that only allows users to
 * type in numbers
 * 
 * @author Joe Lilien
 *
 */
public abstract class InputEntryView extends EntryView {

    private GridPane myContainer;
    private TextInputControl myTextInput;
    private double width;
    private double height;

    public InputEntryView (String label,
                           IFormDataManager data,
                           double width,
                           double height,
                           String cssClass) {
        super(label, data);
        this.width = width;
        this.height = height;

    }

    @Override
    protected void init (String label, String cssClass) {
        this.myTextInput.setMinSize(width, height);
        this.myTextInput.setMaxSize(width, height);
        this.myTextInput.textProperty().bindBidirectional(getData().getValueProperty());
        this.myContainer = new GridPane();
        myContainer.add(getLabel(), 0, 0);
        myContainer.add(myTextInput, 0, 1);
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
