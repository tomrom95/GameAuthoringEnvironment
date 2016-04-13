package gameauthoring.creation.entryviews;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;


public class NameCellView<E extends IProfilable> extends ListCell<E> {

    private static final String DEFAULT_NAME = "<No Name>";

    private E myProfile;

    @Override
    protected void updateItem (E item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            myProfile = null;
            System.out.println("It's null");
        }
        else {
            myProfile = item;
        }
    }

    protected Node createSpriteCell (E profile) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(createTextProfile(profile.getProfile()));
        return container;
    }

    private Node createTextProfile (IProfile profile) {
        VBox container = new VBox();
        Text name = new Text(getStringOrDefault(profile.getName(), DEFAULT_NAME));
        container.getChildren().addAll(name);
        return container;
    }

    private String getStringOrDefault (String name, String defaultName) {
        return (name == "") ? defaultName : name;
    }

    protected E getProfile () {
        return myProfile;
    }

}
