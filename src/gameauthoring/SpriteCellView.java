package gameauthoring;


import gameauthoring.characters.IListCellView;
import java.util.function.Consumer;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class SpriteCellView extends ListCell<ObjectProperty<ISprite>>{
    
    private static final double PIC_SIZE = 30;
    private static final String DEFAULT_NAME = "<No Name>";
    
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
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(createImageProfile());
        container.getChildren().add(createTextProfile());
        return container;
    }
    
    private Node createTextProfile () {
        VBox container = new VBox();
        
        Text name = new Text(getNameString());
        Text description = new Text("");
        container.getChildren().addAll(name, description);
        return container;
    }
    
    private String getNameString(){
        SpriteType spriteType = getSprite().get().getType().get();
        String nameString = (spriteType == null) ? DEFAULT_NAME : spriteType.getType();
        return nameString;
    }

    private Node createImageProfile () {
        GraphicFactory graphics = new ScaleFactory(PIC_SIZE, PIC_SIZE);
        Node node = mySprite.get().getDrawer().get().getVisualRepresentation(graphics);
        return node;
    }
    
    protected ObjectProperty<ISprite> getSprite(){
        return mySprite;
    }

   

}
