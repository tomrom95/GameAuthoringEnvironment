package gameplayer;

import java.util.ResourceBundle;
import engine.IGame;
import engine.IOInterpeter;
import engine.rendering.IRenderer;
import engine.rendering.InGameRenderer;
import engine.rendering.LevelRenderer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.ScaleRatio;
import util.TimeDuration;


/**
 * This class represents a GameEngine and handles the rendering and updating of a game
 * 
 * @author David Maydew
 *
 */
public class GameEngine implements IGameEngine {

    private static final String PATH = "defaults/gameplayer";
    private static final int FPS = 60;
    private ResourceBundle myBundle = ResourceBundle.getBundle(PATH);

    private IGame myGame;
    private UserDisplay myDisplay;
    private LevelRenderer myRenderer;
    private IOInterpeter myIOIntercepter;
    private SideBarDisplay mySideBar;
    private GamePlayerTools myTools;
    private Timeline myTimeline = new Timeline();
    private ScaleRatio myScale = new ScaleRatio();
    private Stage myStage;

    public GameEngine (IGame game,
                       Stage stage,
                       BorderPane gamePane,
                       Pane levelPane,
                       IOInterpeter ioInterpreter) {
        myGame = game;
        myStage = stage;
        myDisplay = new UserDisplay(myGame);
        myRenderer = new InGameRenderer(game, levelPane, myDisplay.getSpriteDisplay(), myScale);
        myIOIntercepter = ioInterpreter;
        myIOIntercepter.setScale(myScale);
        initDisplays();
        createLevelView(gamePane);
        initializeTimeline();
    }

    private void initDisplays () {
        mySideBar = new PlayerSideBar(myGame, myRenderer, myScale);
        myTools = new GamePlayerTools(this);
    }

    private void createLevelView (BorderPane gamePane) {

        gamePane.setRight(mySideBar.draw());
        gamePane.setLeft(myDisplay.draw());
        gamePane.setTop(myTools.draw());
        gamePane.setCenter(myRenderer.getPane());
        addRendererClip();
    }

    /**
     * Clipping approach found here:
     * http://www.coderanch.com/t/636524/JavaFX/java/Scaling-Node-clipping
     */
    private void addRendererClip () {
        Rectangle clip = new Rectangle();
        myRenderer.getPane().setClip(clip);
        myRenderer.getPane().layoutBoundsProperty().addListener( (observable, oldVal, newVal) -> {
            clip.setWidth(myScale.scale(myGame.getLevelBounds().getWidth()));
            clip.setHeight(myScale.scale(myGame.getLevelBounds().getHeight()));
        });
    }

    private void initializeTimeline () {
        Timeline timeline = getTimeline();
        Duration frameDuration = Duration.seconds(1.0d / FPS);
        KeyFrame repeatedFrame = new KeyFrame(frameDuration, e -> step(frameDuration));
        timeline.getKeyFrames().add(repeatedFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void step (Duration frameDuration) {
        getGame().internalizeKeyEvents(myIOIntercepter.deQueueKeyEvents());
        getGame().internalizeMouseEvents(myIOIntercepter.deQueueMouseEvents());
        getGame().update(new TimeDuration(frameDuration.toMillis()));
        if (getGame().getSwitched()) {
            rescale(myStage.getWidth(), myStage.getHeight());
        }
        getRenderer().render();
        
    }

    @Override
    public void play () {
        getTimeline().play();
    }

    @Override
    public void pause () {
        getTimeline().pause();
    }

    public IGame getGame () {
        return myGame;
    }

    private IRenderer getRenderer () {
        return myRenderer;
    }

    private Timeline getTimeline () {
        return myTimeline;
    }

    public void rescale (double width, double height) {
        double xScale = rescaleX(width);
        double yScale = rescaleY(height);
        double min = Math.min(xScale, yScale);
        myScale.setScale(min);
        myRenderer.redrawBackground();
    }

    private double rescaleX (double width) {
        double remaining = width - mySideBar.getWidth() - myDisplay.getWidth();
        double levelWidth = myGame.getLevelManager().getCurrentLevel().getBounds().getWidth();
        return remaining / levelWidth;
    }

    private double rescaleY (double height) {
        double left = height - myTools.getHeight();
        double levelHeight = myGame.getLevelManager().getCurrentLevel().getBounds().getHeight();
        return left / levelHeight;
    }

}
