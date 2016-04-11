package gameauthoring.application;

import engine.Game;
import engine.ICondition;
import engine.IGame;
import engine.OnClickCondition;
import engine.definitions.SpriteDefinition;
import gameauthoring.AuthoringView;
import gameauthoring.conditiontab.ConditionView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;


public class TestMain extends Application {

    @Override
    public void start (Stage s) throws Exception {
        Stage stage = new Stage();
        Group root = new Group();
        ConditionView myConditionView =
                new ConditionView(new Tab(), getTestConditions(), getOptions(), getGame());
        root.getChildren().add(myConditionView.draw());
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }

    private IGame getGame () {
       IGame game = new Game(null, null, null);
       game.getAuthorshipData().getCreatedSprites().add(new SpriteDefinition());
       return game;
    }

    public static void main (String[] args) {
        launch(args);
    }

    private ObservableList<String> getOptions () {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("OnClickCondition");
        options.add("OnCollisionCondition");
        options.add("OnGlobalAttributeCondition");
        options.add("OnSpriteAttributeCondtion");
        return options;
    }

    private ObservableList<ICondition> getTestConditions () {
        ObservableList<ICondition> conditions = FXCollections.observableArrayList();
        return conditions;
    }

}
