package gameauthoring.util;

import java.util.List;
import gameauthoring.creation.cellviews.ProfileCellView;
import graphics.ImageGraphic;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.Optional;
import engine.rendering.GraphicFactory;
import engine.rendering.UnscaledFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * 
 * @author Tommy, Jin An, Joe Lilien
 *
 */
public class UIFactory {

    public Button createButton (String text, EventHandler<ActionEvent> action) {
        Button newButton = new Button(text);
        newButton.setOnAction(action);
        return newButton;
    }

    public Button createImageButton (Node imgview,
                                     EventHandler<ActionEvent> action) {
        Button newButton = new Button();
        newButton.setGraphic(imgview);
        newButton.setOnAction(action);
        return newButton;
    }

    public Tab createTab (boolean closable, Node content) {
        Tab newTab = new Tab();
        newTab.setClosable(closable);
        newTab.setContent(content);
        return newTab;
    }

    public Tab createTabText (String text, boolean closable, Node content) {
        Tab tab = createTab(closable, content);
        tab.setText(text);
        return tab;
    }

    public Tab createTabGraphic (Node graphic, boolean closable, Node content) {
        Tab tab = createTab(closable, content);
        tab.setGraphic(graphic);
        return tab;
    }

    public Image getImageFromNode (Node node) {
        return node.snapshot(new SnapshotParameters(), null);
    }

    public ScrollPane makeScrollPane (Node content, int width, int height) {
        ScrollPane pane = new ScrollPane(content);
        pane.setPrefSize(width, height);
        return pane;
    }

    public HBox makeHBox (double spacing, Pos alignment, Node ... content) {
        HBox box = new HBox(spacing);
        if (content != null) {
            box.getChildren().addAll(content);
        }
        box.setAlignment(alignment);
        return box;
    }

    public VBox makeVBox (double spacing, Node ... content) {
        VBox box = new VBox(spacing);
        if (content != null) {
            box.getChildren().addAll(content);
        }
        return box;
    }

    public Node makeImageDisplay (String imageURL, String label) {
        Label tag = new Label(label);
        ImageView graphic = makeImageView(imageURL, 50, 50);
        VBox box = makeVBox(5, graphic, tag);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public ImageView makeImageView (String imageURL, DoubleProperty width, DoubleProperty height) {
        ImageView image = new ImageView(new Image(getClass().getClassLoader()
                .getResourceAsStream(imageURL)));
        image.fitWidthProperty().bind(width);
        image.fitHeightProperty().bind(height);
        return image;

    }

    public ImageView makeImageView (String imageURL, double width, double height) {
        ImageView image =
                makeImageView(imageURL, new SimpleDoubleProperty(width),
                              new SimpleDoubleProperty(height));
        return image;

    }

    public Slider makeSlider (double min, double max, boolean showTicks, boolean showLabels) {
        Slider slider = new Slider(min, max, min + (max - min) / 2);
        slider.setShowTickMarks(showTicks);
        slider.setShowTickLabels(showLabels);
        return slider;
    }

    public TitledPane makeTitledPane (String title, Node content, boolean isExpanded) {
        TitledPane pane = new TitledPane(title, content);
        pane.setExpanded(isExpanded);
        return pane;
    }

    public void modifyListView (ListView<?> listView,
                                double width,
                                double height,
                                String cssClass) {
        listView.setPrefSize(width, height);
        listView.getStyleClass().add(cssClass);
    }

    public Accordion makeAccordion (double width) {
        Accordion item = new Accordion();
        item.setMaxWidth(width);
        return item;
    }

    public Optional<String> getTextDialog (String holder,
                                           String title,
                                           String content) {
        TextInputDialog dialog = new TextInputDialog(holder);
        dialog.setTitle(title);
        dialog.setContentText(content);
        return dialog.showAndWait();
    }
}
