package gameauthoring;

import engine.ISprite;
import gameauthoring.characters.IListCellView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SpriteCellView implements IListCellView{
    
    private ISprite mySprite;

    public SpriteCellView (ISprite sprite){
        mySprite = sprite;
    }
    
    @Override
    public Node draw () {
        HBox container = new HBox();
        container.getChildren().add(createImageProfile());
        container.getChildren().add(createTextProfile());
        return container;
    }
    
    private Node createTextProfile () {
        VBox container = new VBox();
        //Text name = new Text(mySprite.getProfileProperty().getName());
        Text name = new Text("<Title>");
        Text description = new Text("<DESCRIPTION>");
        container.getChildren().addAll(name, description);
        return container;
    }

    private Node createImageProfile () {
        //Node image = mySprite.getDrawer().get().getVisualRepresentation();
        Node image = new ImageView(new Image("images/photo.png"));
        return image;
    }

    @Override
    public void update () {
        //
    }

}
