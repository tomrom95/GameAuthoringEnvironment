package gameplayer;

import engine.IAttribute;
import engine.IGame;
import facebookutil.JavaSocial;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Game player HUD that displays the user information (TODO)
 * and shows all the global attributes, updating automatically
 * 
 * @author Tommy
 *
 */
public class HeadsUpDisplay extends SizeableGlyph {

    private IGame myGame;
   
    public HeadsUpDisplay (IGame game) {
        myGame = game;
    }

    @Override
    public Node draw () {
        VBox container = new VBox(parseString(getString("HUDCushion")));
        container.setPrefWidth(parseString(getString("HUDWidth")));
        container.setPrefHeight(parseString(getString("HUDHeight")));
        addUser(container);
        addAttributes(container);
        return container;
    }

    /**
     * TODO create user information
     * 
     * @param container
     */
    private void addUser (VBox container) {
        // TODO implement users
        Text user = new Text(JavaSocial.getInstance().getActiveUser().toString());
        container.getChildren().add(user);
    }

    /**
     * Add global attributes to the HUD
     * 
     * @param container
     */
    private void addAttributes (VBox container) {
        myGame.getGlobalAttributes()
                .stream()
                .forEach(a -> addAttribute(container, a));
        myGame.getLevelManager().getCurrentLevel().getAttributeManager().getAttributes()
                .stream()
                .forEach(a -> addAttribute(container, a));
    }

    /**
     * Adds an attribute cell based on the global attribute
     * 
     * @param container
     * @param attribute
     */
    private void addAttribute (VBox container, IAttribute attribute) {
        container.getChildren().add(new AttributeCell(attribute).draw());
    }
}
