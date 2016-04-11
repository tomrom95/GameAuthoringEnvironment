package gameplayer;

import engine.IAttribute;
import engine.IGame;
import gameauthoring.Glyph;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HeadsUpDisplay implements Glyph{
    
    public IGame myGame;
    
    public HeadsUpDisplay (IGame game) {
        myGame = game;
    }

    @Override
    public Node draw () {
        VBox container = new VBox(5);
        addUser(container);
        addAttributes(container);
        return container;
    }

    private void addUser (VBox container) {
        // TODO implement users
        Node user = new Text("User Name: TODO");
        container.getChildren().add(user);
    }
    
    private void addAttributes (VBox container) {
        myGame.getGlobalAttributes()
              .stream()
              .forEach(a -> addAttribute(container, a));
    }

    private void addAttribute (VBox container, IAttribute attribute) {
        container.getChildren().add( (new AttributeCell(attribute)).draw());
    }

    @Override
    public void update () {
    }
    
    

}
