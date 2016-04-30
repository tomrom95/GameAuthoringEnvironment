package gameauthoring.creation.subforms.fire;

import java.util.List;
import gameauthoring.creation.subforms.ClickAndFillView;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * Implementation of IFiringSFVmult with button display
 * 
 * @author Joe Lilien
 *
 */
public class FiringSFV extends ClickAndFillView {

    private String defaultHelpMessage = getMyLabels().getString("HelpMessage");

    public FiringSFV (List<String> options, String titleKey) {
        super(options, titleKey);
        initView();
    }

    @Override
    public void addOrSetSFV (ISubFormView subFormView) {
        super.getMyPaneContent().getChildren().add(subFormView.draw());
    }

}
