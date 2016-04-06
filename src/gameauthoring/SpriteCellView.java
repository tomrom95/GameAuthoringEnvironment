package gameauthoring;

import engine.ISprite;
import graphics.GraphicFactory;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SpriteCellView extends ListCell<ObjectProperty<ISprite>>{
    
    private ObjectProperty<ISprite> mySprite;

    @Override
    protected void updateItem(ObjectProperty<ISprite> item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            mySprite = item;
            setGraphic(createSpriteCell());
        }
    } 
    
    protected Node createSpriteCell () {
        HBox container = new HBox();
        
        container.getChildren().add(createImageProfile());
        container.getChildren().add(createTextProfile());
        return container;
    }
    
    private Node createTextProfile () {
        VBox container = new VBox();
        Text name = new Text("Title");
        Text description = new Text("<DESCRIPTION>");
        container.getChildren().addAll(name, description);
        return container;
    }

    private Node createImageProfile () {
        GraphicFactory graphics = new GraphicFactory();
        Node node = mySprite.get().getDrawer().get().getVisualRepresentation(graphics);
        return node;
    }
    
    protected ObjectProperty<ISprite> getSprite(){
        return mySprite;
    }
    
}
