package gameauthoring.creation.entryviews;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Slider;


/**
 * Entry View class that lets users control a double value through a slider
 * 
 * @author Joe Lilien
 *
 */
public class SliderEntryView extends EntryView {

    private Slider mySlider;

    public SliderEntryView (String label, String cssClass, double min, double max) {
        super(label, cssClass);
        mySlider = getMyFactory().makeSlider(min, max, false, false);
        init();
    }

    @Override
    public Node draw () {
        return getMyContainer();
    }

    @Override
    protected void init () {
        getMyContainer().getChildren().add(mySlider);
    }

    public double getData () {
        return mySlider.getValue();
    }

    public void setData (double data) {
        mySlider.setValue(data);
    }

    public DoubleProperty getValueProperty () {
        return mySlider.valueProperty();
    }

}
