package testing;

import java.util.ArrayList;
import java.util.List;
import engine.IOInterpeter;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IMovementModule;
import engine.modules.PathMover;
import engine.modules.UserMover;
import engine.rendering.InGameRenderer;
import engine.sprite.Sprite;
import gameplayer.GamePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.ControlKeys;
import util.Coordinate;
import util.Key;
import util.RGBColor;
import graphics.Block;
import util.TimeDuration;


public class Launcher extends Application {

    private IOInterpeter myIO;
    private Sprite mySprite;
    private Pane myPane;
    private InGameRenderer myRenderer;

    public static void main (String[] args) {
        launch(args);
    }

    private void initializeTimeline () {
        Timeline timeline = new Timeline();
        Duration frameDuration = Duration.seconds(1.0d / 60);
        KeyFrame repeatedFrame = new KeyFrame(frameDuration, e -> step(frameDuration));
        timeline.getKeyFrames().add(repeatedFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void step (Duration frameDuration) {
        mySprite.update(new TimeDuration(10));
        myIO.deQueueKeyEvents().forEach(e -> mySprite.registerKeyEvent(e));
        myRenderer.render();
        myRenderer.draw(mySprite);

    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        Stage myStage = new Stage();
        myPane = new Pane();
        myPane.setStyle("-fx-background-color: blue;");
        Scene myScene = new Scene(myPane);
        myPane.setPrefSize(1000, 400);
        myStage.setScene(myScene);
        myStage.show();
        myPane.setOnKeyPressed(e -> System.out.println("Pane"));
        myIO = new IOInterpeter(myScene, myPane);
        myPane.requestFocus();
        ControlKeys keys =
                new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(100, 100));
        list.add(new Coordinate(20, 20));
        list.add(new Coordinate(200, 2));
        list.add(new Coordinate(500, 30));
        mySprite = new Sprite();
        ObjectProperty<IMovementModule> mover =
                new SimpleObjectProperty<>(new PathMover(.10, list, mySprite));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(20, 20, RGBColor.BLACK)));
        mySprite.getMovementStrategyProperty().set(mover.get());
        mySprite.getDrawer().set(g.get());
        initializeTimeline();
        myRenderer = new InGameRenderer(null, myPane);
        myStage.show();
        myRenderer.draw(mySprite);
    }

}
