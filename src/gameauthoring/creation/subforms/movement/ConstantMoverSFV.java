package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import splash.LocaleManager;
import util.StringParser;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a sub form that creates the information required to build a constant mover
 * module
 * 
 * @author Joe Lilien
 * @author Dhrumil
 *
 */
public class ConstantMoverSFV extends SubFormView implements IConstantMoverSFV {

    private HBox myPane;
    private ResourceBundle myLabel;
    private String mySpeedKey;
    private String myOrientationKey;
    private NumberEntryView mySpeed;
    private NumberEntryView myOrientation;

    public ConstantMoverSFV () {
        setResourceBundleAndKey();
        createEntryViews();
        initView();
    }

    private void createEntryViews () {
        double width = getParser().parseDouble(getMyNumbers().getString("WidthMovement"));
        double height = getParser().parseDouble(getMyNumbers().getString("Height"));
        mySpeed =
                new NumberEntryView(mySpeedKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myOrientation =
                new NumberEntryView(myOrientationKey, width, height,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        mySpeedKey = myLabel.getString("SpeedKey");
        myOrientationKey = myLabel.getString("OrientationKey");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        double spacing = getParser().parseDouble(getMyNumbers().getString("HBoxSpacing"));
        myPane =
                getMyUIFactory().makeHBox(spacing, Pos.CENTER, mySpeed.draw(),
                                          myOrientation.draw());
        myPane.getStyleClass().add("mover");
    }

    @Override
    public double getMySpeed () {
        return mySpeed.getData();
    }

    @Override
    public double getMyOrientation () {
        return myOrientation.getData();
    }

    @Override
    public void populateWithData (double orientation, double speed) {
        myOrientation.setData(orientation);
        mySpeed.setData(speed);
    }

}
