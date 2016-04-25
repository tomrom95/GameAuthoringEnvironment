package gameauthoring.util;

import java.util.List;
import gameauthoring.creation.cellviews.ProfileCellView;
import graphics.ImageGraphic;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.Optional;
import engine.rendering.GraphicFactory;
import engine.rendering.UnscaledFactory;
import java.util.ResourceBundle;
import engine.profile.IProfilable;
import engine.rendering.ScaleFactory;
import gameauthoring.creation.cellviews.NameCellView;
import graphics.IGraphic;
import javafx.collections.ObservableList;
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
 * Interface defining what a UIFactory must be able to create
 * 
 * @author RyanStPierre
 *
 */

public interface UIFactory {
     
    Button createButton (String text, EventHandler<ActionEvent> action);

    Button createStyledButton (String text,
                                      EventHandler<ActionEvent> action,
                                      String styleClass);

    Button createStyledButton (String text,
                                      String styleClass);

 
     Button createImageButton (String text,
                                     ImageView imgview,
                                     EventHandler<ActionEvent> action);

     Button createImageButton (String url);

    Tab createTab (String text, boolean closable, Node content);

    Image getImageFromNode (Node node);

    <T extends IProfilable> ComboBox<T> createCombo (ObservableList<T> list);

    TextField createTextField (double width);

    Optional<String> getTextDialog (String holder,
                                    String title,
                                    String content);

    Label createLabel (String title);

    Label createTitleLabel (String title);

    Node createTitledSlider (String title, Slider s);

    Slider createSlider (double min, double max, double start, double increment);

    Node createImageView (IGraphic image, double width, double height);

    Button createButton (String title);

    Label createSubTitleLabel (String string);

}
