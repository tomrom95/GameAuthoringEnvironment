package library;

import engine.IGame;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class GamePosterFactory {

    public Node createGamePoster (IGame game) {
        VBox vBox = new VBox();
        vBox.setId("poster");
        vBox.getChildren()
                .add(createImage(game));
        vBox.getChildren().add(createLabel(game.getGameInformation().getNameProperty().get()));
        vBox.getChildren().add(createLabel(game.getGameInformation().getAuthorProperty().get()));
        return vBox;
    }

    private Label createLabel (String contents) {
        Label label = new Label(contents);
        label.setId("gamelabel");
        return label;
    }

    private ImageView createImage (IGame game) {
        ImageView iView = new ImageView(new Image(game.getGameInformation().getSplashScreenURL()));
        iView.setFitHeight(200);
        iView.setFitWidth(200);
        return iView;
    }
}
