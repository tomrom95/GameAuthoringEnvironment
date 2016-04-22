package gameauthoring.waves;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveDataDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class DataAuthorshipView implements Glyph {

    private UIFactory myFactory = new UIFactory();
    private GridPane myPane = new GridPane();
    private ObservableList<WaveDataDefinition> myDataList;
    private Button myCreate;
    private ComboBox<SpriteDefinition> mySpriteChoices;
    
    public DataAuthorshipView (IGame game, ObservableList<WaveDataDefinition> list) {
        myCreate = new Button("Create");
        mySpriteChoices = myFactory.createCombo(game.getAuthorshipData().getAllCreatedSprites());
        myDataList = list;
        init();
        new DataAuthorshipController(this);
    }
    
    private void init () {
        myPane.add(myCreate, 1, 1);
        myPane.add(mySpriteChoices, 1, 0);
        
    }

    @Override
    public Node draw () {
       return myPane;
    }

    public void setOnButtonAction (EventHandler<MouseEvent> event) {
        myCreate.setOnMouseClicked(event);     
    }

    public void addToList () {
        myDataList.add(getWaveDataDefinition());
    }

    private WaveDataDefinition getWaveDataDefinition () {
        WaveDataDefinition myData = new WaveDataDefinition(getSpriteDef(), getCount());
        return myData;
    }

    private SpriteDefinition getSpriteDef () {
        // TODO Auto-generated method stub
        return null;
    }

    private int getCount () {
        // TODO Auto-generated method stub
        return 0;
    }

}
