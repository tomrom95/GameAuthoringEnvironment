package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.dynamic.MultiSFController;
import splash.LocaleManager;
import util.StringParser;


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
    private ResourceBundle myNumbers;
    private StringParser myParser;

    public RemovableSFC (MultiSFController<T> sfc) {
        myLabels =
                ResourceBundle
                        .getBundle("languages/labels",
                                   LocaleManager.getInstance().getCurrentLocaleProperty().get());
        myNumbers = ResourceBundle
                .getBundle("defaults/numbers");
        myParser = new StringParser();
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

    protected ResourceBundle getMyNumbers () {
        return myNumbers;
    }

    protected StringParser getParser () {
        return myParser;
    }

}
