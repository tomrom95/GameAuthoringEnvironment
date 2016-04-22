package gameauthoring.creation.entryviews;

import gameauthoring.util.UIFactory;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class SliderEntryView extends EntryView{
    private GridPane myContainer;
    private Slider mySlider;
    private UIFactory myUIFactory = new UIFactory();

    public SliderEntryView (String label, String cssClass, double min, double max) {
        super(label);
        myContainer = new GridPane();
        mySlider = myUIFactory.makeSlider(min, max, true, true);
        init(label,cssClass);
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    protected void init (String label, String cssClass) {
        myContainer.getStyleClass().add(cssClass);
        myContainer.add(new Label(label), 0, 0);
        myContainer.add(mySlider, 0, 1);
    }
    
    public DoubleProperty getValueProperty(){
        return mySlider.valueProperty();
    }

}
