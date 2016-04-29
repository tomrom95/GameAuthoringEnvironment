package library;

import java.util.ResourceBundle;
import engine.IGame;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class GamePosterFactory {
    
    private static final ResourceBundle myBundle = ResourceBundle.getBundle("defaults/poster_factory");

    public Node createGamePoster (IGame game) {
        VBox vBox = new VBox();
        vBox.setId(myBundle.getString("poster"));
        vBox.getChildren()
                .add(createImage(game));
        vBox.getChildren().add(createLabel(game.getGameInformation().getNameProperty().get()));
        vBox.getChildren().add(createLabel(game.getGameInformation().getAuthorProperty().get()));
        return vBox;
    }

    private Label createLabel (String contents) {
        Label label = new Label(contents);
        label.setId(myBundle.getString("gamelabel"));
        return label;
    }

    private ImageView createImage (IGame game) {
        ImageView iView = new ImageView(new Image(game.getGameInformation().getSplashScreenURL()));
        iView.setFitHeight(Double.parseDouble(myBundle.getString("imageHeight")));
        iView.setFitWidth(Double.parseDouble(myBundle.getString("imageWidth")));
        return iView;
    }
}
