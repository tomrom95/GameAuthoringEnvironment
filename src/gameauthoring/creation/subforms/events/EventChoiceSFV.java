package gameauthoring.creation.subforms.events;

import java.util.List;
import gameauthoring.creation.subforms.ClickAndFillView;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.scene.control.Label;


public class EventChoiceSFV extends ClickAndFillView {

    public EventChoiceSFV (List<String> options) {
        super(options);
        initView();
    }

    @Override
    public void addOrSetSFV (ISubFormView subFormView) {
        super.getMyPaneContent().getChildren().add(subFormView.draw());
    }

    @Override
    public void showDefaultMessage () {
        getMyPaneContent().getChildren().add(new Label("insert message from file here"));
    }

}
