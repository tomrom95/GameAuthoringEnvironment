package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ProfileSubFormView extends SubFormView{

    private GridPane myPane = new GridPane();
    private String myNameKey = "Name: ";
    private String myDescriptionKey = "Description: ";
    private String myImageKey = "Image: ";   
    private IEntryView myName = new TextEntryView(myNameKey,this.getData(), 250, 40, AuthoringView.DEFAULT_ENTRYVIEW); // maybe change these
                                                                               // constructor
                                                                               // Arguments to
                                                                               // getters/setters
    private IEntryView myImage = new ImageEntryView(myImageKey,this.getData(), 200, 200, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myDescription = new TextEntryView(myDescriptionKey, this.getData(), 250, 100, AuthoringView.DEFAULT_ENTRYVIEW);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myName,myImage,myDescription));
    

    public ProfileSubFormView () {
        initView();
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myDescription.draw(), 0, 1);
        myPane.add(myImage.draw(), 1, 0,1,2);            
    }
    
    public String getMyNameKey () {
        return myNameKey;
    }


    public String getMyDescriptionKey () {
        return myDescriptionKey;
    }


    public String getMyImageKey () {
        return myImageKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }
}
