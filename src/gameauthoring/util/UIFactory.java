package gameauthoring.util;

import java.util.Optional;
import java.util.ResourceBundle;
import engine.profile.IProfilable;
import engine.rendering.ScaleFactory;
import gameauthoring.creation.cellviews.NameCellView;
import graphics.IGraphic;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * 
 * @author Tommy, Jin An, Joe Lilien
 *
 */
public class UIFactory {

    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private static final double CUSHION = 10;
    
    public Button createButton (String text, EventHandler<ActionEvent> action) {
        Button newButton = new Button(text);
        newButton.setOnAction(action);
        return newButton;
    }

    /**
     * Creates a button with the given CSS style class
     * 
     * @param text
     * @param action
     * @return
     */

    public Button createStyledButton (String text,
                                      EventHandler<ActionEvent> action,
                                      String styleClass) {
        Button newButton = createStyledButton(text, styleClass);
        newButton.setOnAction(action);
        return newButton;
    }

    public Button createStyledButton (String text,
                                      String styleClass) {
        Button newButton = new Button(text);
        addStyling(newButton, styleClass);
        return newButton;
    }

    public Button createImageButton (String text,
                                     ImageView imgview,
                                     EventHandler<ActionEvent> action) {
        Button newButton = new Button(text, imgview);
        newButton.setOnAction(action);
        return newButton;
    }
    
    public Button createImageButton (String url) {
        Image image = new Image(url);
        Button newButton = new Button(null, new ImageView(image));
        return newButton;
    }

    public Tab createTab (String text, boolean closable, Node content) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setClosable(closable);
        newTab.setContent(content);
        return newTab;
    }

    public Image getImageFromNode (Node node) {
        return node.snapshot(new SnapshotParameters(), null);
    }

    public <T extends IProfilable> ComboBox<T> createCombo (ObservableList<T> list) {
        ComboBox<T> box = new ComboBox<>(list);
        addCellFactory(box);
        return box;
    }

    public TextField createTextField (double width) {
        TextField text = new TextField();
        text.setMaxWidth(width);
        return text;
    }

    private <T extends IProfilable> void addCellFactory (ComboBox<T> comboBox) {
        comboBox.setCellFactory(c -> new NameCellView<>());
        comboBox.setButtonCell(new NameCellView<>());

    }

    public Optional<String> getTextDialog (String holder,
                                           String title,
                                           String content) {
        TextInputDialog dialog = new TextInputDialog(holder);
        dialog.setTitle(title);
        dialog.setContentText(content);
        return dialog.showAndWait();

    }

    public Label createLabel (String title) {
        return new Label(title);
    }
    
    public Label createTitleLabel (String title) {
        Label label = createLabel(title);
        addStyling(label, "TitleLabel");
        return label;
    }


    public Node createTitledSlider (String title, Slider s) {
        VBox vbox = new VBox (CUSHION);
        vbox.getChildren().add(createLabel(title));
        vbox.getChildren().add(s);
        return vbox;
    }
    
    public Slider createSlider (double min, double max, double start, double increment) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(start);
        slider.setBlockIncrement(increment);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return slider;
    }

    public Node createImageView (IGraphic image, double width, double height) {
        return image.getVisualRepresentation(new ScaleFactory(width, height));
    }

    public Button createButton (String title) {
        return new Button(title);
    }

    public Label createSubTitleLabel (String string) {
        Label label = createLabel(string);
        addStyling(label, "SubTitleLabel");
        return label;
    }
    
    private void addStyling(Node node, String key) {
        node.getStyleClass().add(myStyle.getString(key));
    }
}
