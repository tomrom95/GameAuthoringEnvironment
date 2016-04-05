package gameauthoring.levels;

import java.io.File;
import engine.ISprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import util.Coordinate;


public class SceneController {

    private PathCreator pathCreator;
    
    public SceneController() {
        pathCreator = new PathCreator();
    }
    
    public void uploadNewBackground (MouseEvent e, Pane container) {
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null)
            return;
        setBackground(container, newImage.toURI().toString());
    }

    public void setBackground (Pane container, String imagePath) {
        Image img = new Image(imagePath, SceneCreator.WIDTH, SceneCreator.HEIGHT, true, true);
        BackgroundImage background = new BackgroundImage(
                                                         img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        container.setBackground(new Background(background));
        container.setMinWidth(img.getWidth());
        container.setMinHeight(img.getHeight());
    }
    
    public void createNewPath(Coordinate startPoint, Pane container){
        pathCreator.newPath(startPoint, container);
        container.setOnMouseClicked(e -> pathCreator.addToPath(e, container));
        container.setOnKeyPressed(e -> handleKeyPress(e, container));
    }
    
    private void handleKeyPress (KeyEvent e, Pane container) {
        if (e.getCode() == KeyCode.ESCAPE) {
            pathCreator.endPath(container);
            System.out.println(pathCreator.getPathPoints());
            // Save path
        }
    }
    
    public void deleteSprite(ISprite sprite){
        
    }
    
}
