package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class ProfileSubFormView extends SubFormView{

    private GridPane myPane = new GridPane();
    private String myNameKey = "Name: ";
    private String myDescriptionKey = "Description: ";
    private String myImageKey = "Image: ";   
    private String myImageWidthKey = "Width: ";
    private String myImageHeightKey = "Height: ";
    private IEntryView myName = new TextEntryView(myNameKey,this.getData(), 250, 40, AuthoringView.DEFAULT_ENTRYVIEW); // maybe change these
                                                                               // constructor
                                                                               // Arguments to
                                                                               // getters/setters
    private IEntryView myImage = new ImageEntryView(myImageKey,this.getData(), 200, 200, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myDescription = new TextEntryView(myDescriptionKey, this.getData(), 250, 150, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myImageWidth = new NumberEntryView(myImageWidthKey, this.getData(), 40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private IEntryView myImageHeight = new NumberEntryView(myImageHeightKey, this.getData(), 40, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myName,myImage,myDescription,myImageWidth,myImageHeight));
    

    public ProfileSubFormView () {
        initView();
    }

    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myDescription.draw(), 0, 1);
        myPane.add(myImage.draw(), 1, 0, 1,2);
        myPane.add(new HBox(myImageWidth.draw(), myImageHeight.draw()), 1, 2);
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
    
    public String getMyImageWidthKey () {
        return myImageWidthKey;
    }
    
    public String getMyImageHeightKey () {
        return myImageHeightKey;
    }

    @Override
    public Node draw () {
        return myPane;
    }
}
