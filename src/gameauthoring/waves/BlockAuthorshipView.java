package gameauthoring.waves;

import java.util.Locale;
import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import util.StringParser;

/**
 * Allows the user to create wave blocks to use in the making of waves
 * @author RyanStPierre
 *
 */

public class BlockAuthorshipView implements Glyph {

    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private ResourceBundle mySize = ResourceBundle.getBundle("defaults/wave_tab_size");
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);

    private UIFactory myFactory = new UIFactory();

    private GridPane myPane = new GridPane();
    private ObservableList<WaveBlockDefinition> myBlockList;
    private TextField myCount;
    private Slider myGap;
    private Button myCreate;
    private ComboBox<SpriteDefinition> mySpriteChoices;

    public BlockAuthorshipView (IGame game, ObservableList<WaveBlockDefinition> list) {
        stylePane();
        factoryGenerate(game);
        myBlockList = list;
        init();
        new BlockAuthorshipController(this);
    }

    private void stylePane () {
        myPane.getStyleClass().add(myStyle.getString("Block"));
        myPane.setPrefSize(Double.parseDouble(mySize.getString("BlockWidth")),
                           Double.parseDouble(mySize.getString("BlockHeight")));

    }

    private void factoryGenerate (IGame game) {
        mySpriteChoices = myFactory.createCombo(game.getAuthorshipData().getAllCreatedSprites());
        myCount = myFactory.createTextField();
        createSlider();
    }

    private void createSlider () {
        double min = 0;
        double max = 0;
        double start =0;
        double tick = 0;
        myGap = myFactory.createSlider(min, max, start, tick);
    }

    private void init () {
        myCreate = myFactory.createButton(myLang.getString("Create"));
        myPane.add(myFactory.createTitleLabel(myLang.getString("BlockTitle")), 0, 0);
        myPane.add(myCount, 3, 1);
        myPane.add(mySpriteChoices, 0, 1);
        myPane.add(myGap, 2, 1);
        myPane.add(myCreate, 3, 2);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void setOnButtonAction (EventHandler<MouseEvent> event) {
        myCreate.setOnMouseClicked(event);
    }

    public void addToList () {

        try {
            myBlockList.add(getWaveBlockDefinition());
        }
        catch (NumberFormatException e) {
            return;
        }
    }

    private WaveBlockDefinition getWaveBlockDefinition () throws NumberFormatException {
        WaveBlockDefinition myBlock = new WaveBlockDefinition(getSpriteDef(), getCount(), getGap());
        return myBlock;
    }

    private double getGap () {
        return myGap.getValue();
    }

    private SpriteDefinition getSpriteDef () {
        return mySpriteChoices.getSelectionModel().getSelectedItem();
    }

    private int getCount () throws NumberFormatException {
        return new StringParser().parseInt(myCount.getText());
    }

}
