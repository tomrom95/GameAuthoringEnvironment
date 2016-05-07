package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import util.StringParser;


/**
 * Easy implementation and reusable class for adding a context menu to an item with some sort of
 * remove functionality, defined in constructor, helps keep separation of model and view
 *
 * @author Joe Lilien
 *
 */
public class RemoveOption implements Glyph {

    private static final String DIMENSION = "HBoxSpacing";
    private Button myRemoveMenu;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private String myImagePath = "images/close.png";
    private ResourceBundle myNumbers;
    private StringParser myParser;

    public RemoveOption (EventHandler<ActionEvent> e) {
        myNumbers = ResourceBundle
                .getBundle("defaults/numbers");
        myParser = new StringParser();
        initRemove(e);
    }

    private void initRemove (EventHandler<ActionEvent> e) {
        double width = myParser.parseDouble(myNumbers.getString(DIMENSION));
        double height = myParser.parseDouble(myNumbers.getString(DIMENSION));
        myRemoveMenu =
                myUIFactory.createImageButton(myUIFactory.makeImageView(myImagePath,
                                                                        width,
                                                                        height),
                                              e);
    }

    @Override
    public Node draw () {
        return myRemoveMenu;
    }

}
