package gameauthoring.util;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import engine.profile.IProfilable;
import engine.rendering.ScaleFactory;
import gameauthoring.creation.cellviews.NameCellView;
import graphics.IGraphic;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * 
 * @author Tommy, Jin An, Joe Lilien
 *
 */
public class BasicUIFactory implements UIFactory {

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
    

    public Tab createTab (String text, boolean closable, Node content) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setClosable(closable);
        newTab.setContent(content);
        return newTab;
    }

    
    public Button createImageButton (Node imgview) {
        Button newButton = new Button();
        newButton.setGraphic(imgview);
        return newButton;
    }
    
    public Button createImageButton (Node imgview,
                                     EventHandler<ActionEvent> action) {
        Button newButton = new Button();
        newButton.setGraphic(imgview);
        newButton.setOnAction(action);
        return newButton;
    }
    
    public Button createImageButton (String url) {
        Image image = new Image(url);
        Button newButton = new Button(null, new ImageView(image));
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
    
    public VBox makeVBox (double spacing, Pos alignment, double width, double height, Node ... content) {
        VBox box = makeVBox(spacing, alignment, content);
        box.setMinSize(width, height);
        return box;
    }

    public VBox makeVBox (double spacing, Pos alignment, Node ... content) {
        VBox box = new VBox(spacing);
        if (content != null) {
            box.getChildren().addAll(content);
        }
        box.setAlignment(alignment);
        return box;
    }

    public Node makeImageDisplay (String imageURL, String label) {
        Label tag = new Label(label);
        ImageView graphic = makeImageView(imageURL, 50, 50);
        VBox box = makeVBox(5, Pos.CENTER, graphic, tag);
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
    public TitledPane makeCheckBoxTitledPane (String title, Node content, boolean isExpanded) {
        TitledPane pane = new TitledPane(title, content);
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(false);
                                                            
        pane.setGraphic(checkBox);
        pane.expandedProperty().bindBidirectional(checkBox.selectedProperty());
        //Node arrow = pane.lookup(".arrow");
        //arrow.setVisible(false);
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
    
    protected void addStyling(Node node, String key) {
        node.getStyleClass().add(myStyle.getString(key));
    }

    @Override
    public TextField createTextField (String holder, double width) {
        TextField field = this.createTextField(width);
        field.setPromptText(holder);
        return field;
        
    }

}
