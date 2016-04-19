package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ProfileSFV extends SubFormView implements IProfileSFV{

    private GridPane myPane = new GridPane();
    private String myNameKey = "Name: "; //TODO: resource file
    private String myDescriptionKey = "Description: ";
    private String myImageKey = "Image: ";   
    private IEntryView myName;                                                                             
    private IEntryView myImage;
    private IEntryView myDescription;
    

    public ProfileSFV () {
        myName = new TextEntryView(myNameKey,this.getData(), 250, 40, AuthoringView.DEFAULT_ENTRYVIEW);
        myImage = new ImageEntryView(myImageKey,this.getData(), 200, 200, AuthoringView.DEFAULT_ENTRYVIEW);
        myDescription = new TextEntryView(myDescriptionKey, this.getData(), 250, 100, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();                
    }

    @Override
    protected void initView () {
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myDescription.draw(), 0, 1);
        myPane.add(myImage.draw(), 1, 0,1,2);            
    }   

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public String getMyNameKey () {
        return this.myNameKey;
    }

    @Override
    public String getMyDescriptionKey () {
        return this.myDescriptionKey;
    }

    @Override
    public String getMyImageKey () {
        return this.myImageKey;
    }
}
