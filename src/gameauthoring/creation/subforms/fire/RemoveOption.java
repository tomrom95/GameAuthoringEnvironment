package gameauthoring.creation.subforms.fire;

import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;


/**
 * Easy implementation and reusable class for adding a context menu to an item with some sort of
 * remove functionality, defined in constructor, helps keep separation of model and view
 * 
 * @author Joe Lilien
 *
 */
public class RemoveOption implements Glyph{

    private Button removeMenu;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    
    public RemoveOption(EventHandler<ActionEvent> e){
        initRemove(e);
    }

    private void initRemove (EventHandler<ActionEvent> e) {
        removeMenu = myUIFactory.createImageButton(myUIFactory.makeImageView(("images/close.png"), 20, 20), e);
    }
    
   
    @Override
    public Node draw () {
        return  removeMenu;
    }

}
