package gameauthoring;

import engine.definitions.ProfileDefinition;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ProfileCellView<E extends ProfileDefinition> extends ListCell<E> {

    private static final double PIC_SIZE = 30;
    private static final String DEFAULT_NAME = "<No Name>";
    private static final String DEFAULT_DESCRIPTION = "<No Description>";

    private E myProfile;

    @Override
    protected void updateItem (E item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            myProfile = item;
            setGraphic(createSpriteCell(item));
        }
    }

    protected Node createSpriteCell (E profile) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        
        container.getChildren().add(createImageProfile(profile));
        container.getChildren().add(createTextProfile(profile));
        return container;
    }

    private Node createTextProfile (ProfileDefinition profile) {
        VBox container = new VBox();

        Text name = new Text(getStringOrDefault(profile.getName(), DEFAULT_NAME));
        Text description = new Text(getStringOrDefault(profile.getDescription(),
                                                       DEFAULT_DESCRIPTION));
        container.getChildren().addAll(name, description);
        return container;
    }

    private String getStringOrDefault (String name, String defaultName) {
        return (name == null) ? defaultName : name;
    }

    private Node createImageProfile (ProfileDefinition profile) {
        GraphicFactory graphics = new ScaleFactory(PIC_SIZE, PIC_SIZE);
        Node node = new HBox(5);//myProfile.getGraphic().getVisualRepresentation(graphics);
        return node;
    }
    
    protected E getProfile () {
        return myProfile;
    }

}
