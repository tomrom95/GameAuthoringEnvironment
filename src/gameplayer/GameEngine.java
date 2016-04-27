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
    ResourceBundle myBundle = ResourceBundle.getBundle(PATH);
    
    private static final int FPS = 60;
    private IGame myGame;
    private UserDisplay myDisplay;
    private LevelRenderer myRenderer;
    private IOInterpeter myIOIntercepter;
    private SideBarDisplay mySideBar;
    private GamePlayerTools myTools;
    private Timeline myTimeline = new Timeline();
    private ScaleRatio myScale = new ScaleRatio();

    public GameEngine (IGame game, BorderPane gamePane, Pane levelPane, IOInterpeter ioInterpreter) {
        myGame = game;
        myDisplay = new UserDisplay(myGame);
        myRenderer = new InGameRenderer(game, levelPane, myDisplay.getSpriteDisplay(), myScale);
        myIOIntercepter = ioInterpreter;
        myIOIntercepter.setScale(myScale);
        initDisplays();
        createLevelView(gamePane);
        initializeTimeline();
    }
    
    private void initDisplays(){
        mySideBar = new PlayerSideBar(myGame, myRenderer, myScale);
        myTools = new GamePlayerTools(this);
    }

    private void createLevelView (BorderPane gamePane) {
        gamePane.setCenter(myRenderer.getPane());
        gamePane.setRight(mySideBar.draw());
        gamePane.setLeft(myDisplay.draw());
        gamePane.setTop(myTools.draw());
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
        System.out.println("XScale " + xScale + " yScale: " + yScale);
        double min = Math.min(xScale, yScale);
        myScale.setScale(min);
        myRenderer.redrawBackground();
    }
    
    private double rescaleX (double room) {
        double sidebar = mySideBar.getWidth();
        double userdisplay = myDisplay.getWidth();
        double left = room - sidebar - userdisplay;
        double g = myGame.getLevelManager().getCurrentLevel().getBounds().getWidth();
        System.out.println("Width left" + left);
        System.out.println("width" + g);
        return left/g;
    }

    private double rescaleY (double room) {
        double toolbar = myTools.getHeight();
        double left = room - toolbar;
        double g = myGame.getLevelManager().getCurrentLevel().getBounds().getHeight();
        System.out.println("Height left" + left);
        System.out.println("height" + g);
        return left/g;
    }

}
