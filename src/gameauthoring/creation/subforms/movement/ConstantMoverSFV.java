package gameauthoring.creation.subforms.movement;

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
    private String mySpeedKey = "Speed: ";
    private String myOrientationKey = "Initial Orientation: ";
    private NumberEntryView mySpeed;
    private NumberEntryView myOrientation;

    public ConstantMoverSFV () {
        mySpeed =
                new NumberEntryView(mySpeedKey, 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myOrientation =
                new NumberEntryView(myOrientationKey, 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = getMyUIFactory().makeHBox(20, Pos.CENTER, mySpeed.draw(), myOrientation.draw());
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
    public void populateWithData(double orientation, double speed) {
        myOrientation.setData(orientation);
        mySpeed.setData(speed);
    }

}
