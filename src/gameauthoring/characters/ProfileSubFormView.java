package gameauthoring.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ProfileSubFormView extends TempProfileSubFormView{

    private GridPane myPane = new GridPane();
    private EntryView myName = new TextEntryView("Name: ", 20, 10, 40, false); // maybe change these
                                                                               // constructor
                                                                               // Arguments to
                                                                               // getters/setters
    private EntryView myImage = new ImageEntryView("Image: ", 20, 20, 20);
    private EntryView myDescription = new TextEntryView("Description: ", 20, 40, 60, false);
    private List<EntryView> myEntryViews;
    

    public ProfileSubFormView () {
        myEntryViews.add(getMyNameInd(), myName);
        myEntryViews.add(getMyImageInd(), myImage);
        myEntryViews.add(getMyDescriptionInd(), myDescription);
        init();
    }

    @Override
    protected void init () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myImage.draw(), 0, 1);
        myPane.add(myDescription.draw(), 1, 0, 1, 1);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }
    

}
