package gameauthoring.creation.cellviews;

import java.util.ResourceBundle;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.StringParser;


/**
 * Class to help visualize a profilable definition in a list view with only name.
 * Anything that implements IProfilable can use this to create its
 * cells in a user friendly way.
 *
 * @author Jeremy Schreck
 * @author Jin An
 *
 * @param <E>
 */
public class NameCellView<E extends IProfilable> extends ListCell<E> {

    private E myProfile;
    private ResourceBundle myNumbers = ResourceBundle
            .getBundle("defaults/numbers");
    private StringParser myParser = new StringParser();

    @Override
    protected void updateItem (E item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            myProfile = null;
            setGraphic(null);
        }
        else {
            myProfile = item;
            setGraphic(createSpriteCell(item));
        }
    }

    protected Node createSpriteCell (E profile) {
        double size = myParser.parseDouble(myNumbers.getString("HBoxStandardSize"));
        HBox container = new HBox(size);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(createTextProfile(profile.getProfile()));
        return container;
    }

    private Node createTextProfile (IProfile profile) {
        VBox container = new VBox();
        Text name = bindText(profile.getName());
        container.getChildren().addAll(name);
        return container;
    }

    private Text bindText (StringProperty name) {
        Text text = new Text();
        text.textProperty().bind(name);
        return text;
    }

    protected E getProfilable () {
        return myProfile;
    }

}
