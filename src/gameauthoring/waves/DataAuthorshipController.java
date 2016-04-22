package gameauthoring.waves;

public class DataAuthorshipController {

    private DataAuthorshipView myView;
    
    public DataAuthorshipController (DataAuthorshipView view) {
        myView = view;
        setAction();
    }

    private void setAction () {
        myView.setOnButtonAction(e -> myView.addToList());
        
    }
}
