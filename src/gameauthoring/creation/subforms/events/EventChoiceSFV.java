package gameauthoring.creation.subforms.events;

import java.util.List;
import gameauthoring.creation.subforms.ClickAndFillView;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.scene.control.Label;


public class EventChoiceSFV extends ClickAndFillView {
    
    private String defaultHelpMessage = "help";

    public EventChoiceSFV (List<String> options) {
        super(options);
        initView();
        setDefaultHelpMessage(defaultHelpMessage);
        showDefaultMessage();
    }

    @Override
    public void addOrSetSFV (ISubFormView subFormView) {
        super.getMyPaneContent().getChildren().add(subFormView.draw());
    }

}
