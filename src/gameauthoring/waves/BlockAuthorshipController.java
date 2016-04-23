package gameauthoring.waves;

/**
 * Controller for the block authorship view
 * @author RyanStPierre
 *
 */
public class BlockAuthorshipController {

    private BlockAuthorshipView myView;
    
    public BlockAuthorshipController (BlockAuthorshipView view) {
        myView = view;
        setAction();
    }

    private void setAction () {
        
        myView.setOnButtonAction(e -> myView.addToList());
        
    }
}
