package gameauthoring.waves;

import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import splash.LocaleManager;
import util.StringParser;


/**
 * Allows the user to create wave blocks to use in the making of waves
 *
 * @author RyanStPierre
 *
 */

public class BlockAuthorshipView implements Glyph {

    private static final int SEC_TO_MILLI = 1000;
    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private ResourceBundle mySize = ResourceBundle.getBundle("defaults/wave_tab_size");
    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());

    private UIFactory myFactory = new BasicUIFactory();

    private GridPane myPane = new GridPane();
    private ObservableList<WaveBlockDefinition> myBlockList;
    private TextField myCount;
    private Slider myGapSlider;
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
        mySpriteChoices =
                myFactory.createCombo(game.getAuthorshipData().getAllCreatedSpritesAsList());
        mySpriteChoices.setOnMouseClicked(e -> repopulate(game));
        mySpriteChoices.setMinWidth(Double.parseDouble(mySize.getString("ComboWidth")));
        myCount = myFactory.createTextField(Double.parseDouble(mySize.getString("TextWidth")));
        createSlider();
    }

    private void repopulate (IGame game) {
        mySpriteChoices.getItems().clear();
        mySpriteChoices.getItems().addAll(game.getAuthorshipData().getAllCreatedSpritesAsList());
    }

    private void createSlider () {
        double min = Double.parseDouble(mySize.getString("SliderMin"));
        double max = Double.parseDouble(mySize.getString("SliderMax"));
        double start = Double.parseDouble(mySize.getString("SliderStart"));
        double tick = Double.parseDouble(mySize.getString("SliderTick"));
        myGapSlider = myFactory.createSlider(min, max, start, tick);
    }

    private void init () {
        myCreate = myFactory.createImageButton(mySize.getString("CreateURL"));
        myPane.add(myFactory.createTitleLabel(myLang.getString("BlockTitle")), 0, 0, 3, 1);
        myPane.add(addLabel(myCount, myLang.getString("Count")), 3, 1, 3, 1);
        myPane.add(addLabel(mySpriteChoices, myLang.getString("SpriteChoice")), 0, 1);
        myPane.add(myCreate, 5, 2);
        myPane.add(addLabel(myGapSlider, myLang.getString("SpawnGapTime")), 2, 1);
    }

    private Node addLabel (Node node, String string) {
        VBox vbox = new VBox(Double.parseDouble(mySize.getString("Cushion")));
        vbox.getChildren().addAll(myFactory.createSubTitleLabel(string), node);
        vbox.setAlignment(Pos.TOP_RIGHT);
        return vbox;
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
        return myGapSlider.getValue() * SEC_TO_MILLI;
    }

    private SpriteDefinition getSpriteDef () {
        return mySpriteChoices.getSelectionModel().getSelectedItem();
    }

    private int getCount () throws NumberFormatException {
        return new StringParser().parseInt(myCount.getText());
    }

}
