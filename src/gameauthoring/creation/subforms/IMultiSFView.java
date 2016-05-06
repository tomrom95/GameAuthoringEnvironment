package gameauthoring.creation.subforms;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface IMultiSFView extends ISubFormView{

    List<Button> getMyViewComponents ();

    void setMyViewComponents (List<Button> myComponents);

    void setViewComponentAction (Button component, EventHandler<ActionEvent> e);

    void addOrSetSFV (ISubFormView subFormView);

    void removeSFV (ISubFormView subFormView);

    void clearSFVs ();

}
