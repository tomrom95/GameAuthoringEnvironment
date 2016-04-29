package gameauthoring.creation.subforms.events;

import java.util.List;
import gameauthoring.creation.subforms.ClickAndFillView;
import gameauthoring.creation.subforms.ISubFormView;


public class EffectChoiceSFV extends ClickAndFillView {
    
    private String defaultHelpMessage = "add default message to languages";

    public EffectChoiceSFV (List<String> options) {
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
