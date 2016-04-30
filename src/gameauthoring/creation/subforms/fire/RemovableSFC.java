package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;
import splash.LocaleManager;


/**
 * 
 * 
 * @author Joe Lilien
 *
 */
public abstract class RemovableSFC<T extends IProfilable> implements ISubFormController<T> {

    private T myDefinition;
    private RemoveOption myView;
    private ResourceBundle myLabels;

    public RemovableSFC (MultiOptionSFC<T> sfc) {
        myLabels =
                ResourceBundle
                        .getBundle("languages/labels",
                                   LocaleManager.getInstance().getCurrentLocaleProperty().get());
        myView = new RemoveOption(e -> sfc.removeSFC(this));
    }

    public abstract void removeModule (Object myMod);

    public abstract Object getModuleDefinition ();

    protected T getMyDefinition () {
        return myDefinition;
    }

    protected RemoveOption getRemoveMenu () {
        return myView;
    }

    protected void setMySpriteDefinition (T mySpriteDefinition) {
        this.myDefinition = mySpriteDefinition;
    }

    protected ResourceBundle getMyLabels () {
        return myLabels;
    }

}
