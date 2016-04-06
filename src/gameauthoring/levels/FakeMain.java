
package gameauthoring.levels;

import engine.ConditionManager;
import engine.Game;
import engine.ILevel;
import engine.sprite.ISprite;
import engine.Level;
import engine.LevelManager;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.sprite.Sprite;
import graphics.Block;
import graphics.ImageGraphic;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.RGBColor;


public class FakeMain extends Application {

    /**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start (Stage stage) {
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        LevelManager levelManager = new LevelManager(startingLevel);
        ConditionManager conditionManager = new ConditionManager();
        Game game = new Game(levelManager, null, conditionManager);
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, startingLevel.get());
        Group root = new Group(view.draw());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void makeSomeSprites (Game game) {
        for (int i = 0; i < 20; i++){
            if (i%2 == 0) {
                game.getAuthorshipData().getCreatedSprites().add(new SimpleObjectProperty<>(createFirstSprite()));
            } else{
                game.getAuthorshipData().getCreatedSprites().add(new SimpleObjectProperty<>(createSecondSprite()));
            }
        }
    }

    private ISprite createFirstSprite () {
        ISprite sprite = new Sprite();
        ObjectProperty<IGraphicModule> g = new SimpleObjectProperty<>(new GraphicModule(new ImageGraphic(30, 30, "images/photo.png")));
        sprite.getDrawer().set(g.get());
        return sprite;
    }
    
    private ISprite createSecondSprite () {
        ISprite sprite = new Sprite();
        ObjectProperty<IGraphicModule> g = new SimpleObjectProperty<>(new GraphicModule(new Block(40, 40, RGBColor.BLACK)));
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}

