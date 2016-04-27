package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.SliderEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Implementation of IProfileSFV with GridPane arrangement of entryviews
 * 
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class ProfileSFV extends SubFormView implements IProfileSFV {

    private GridPane myPane = new GridPane();
    private String myNameKey = "Name: "; // TODO: resource file
    private String myDescriptionKey = "Description: ";
    private String myImageKey = "Image: ";
    private String myImageWidthKey = "Width: ";
    private String myImageHeightKey = "Height: ";
    private TextEntryView myName;
    private ImageEntryView myImage;
    private TextEntryView myDescription;
    private SliderEntryView myImageWidth;
    private SliderEntryView myImageHeight;

    public ProfileSFV () {
        myName =
                new TextEntryView(myNameKey, 250, 40, AuthoringView.DEFAULT_ENTRYVIEW);
        myDescription =
                new TextEntryView(myDescriptionKey, 250, 100, AuthoringView.DEFAULT_ENTRYVIEW);
        myImageWidth =
                new SliderEntryView(myImageWidthKey, AuthoringView.DEFAULT_ENTRYVIEW, 10, 200);
        myImageHeight =
                new SliderEntryView(myImageHeightKey, AuthoringView.DEFAULT_ENTRYVIEW, 10, 200);
        myImage =
                new ImageEntryView(myImageKey, myImageWidth.getValueProperty(),
                                   myImageHeight.getValueProperty(),
                                   AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myDescription.draw(), 0, 1);
        myPane.add(myImage.draw(), 1, 0, 1, 3);
        myPane.add(new HBox(myImageWidth.draw(), myImageHeight.draw()), 0, 2);
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public String getName () {
        return myName.getData();
    }

    @Override
    public String getDescription () {
        return myDescription.getData();
    }

    @Override
    public String getImage () {
        return myImage.getImageURL();
    }

    @Override
    public double getMyImageWidth () {
        return myImageWidth.getValueProperty().get();
    }

    @Override
    public double getMyImageHeight () {
        return myImageHeight.getValueProperty().get();
    }

    @Override
    public void populateWithData (String name,
                                  String description,
                                  String imageURL,
                                  double width,
                                  double height) {
        myName.setData(name);
        myDescription.setData(description);
        myImage.updateImage(imageURL);
        myImageWidth.setData(width);
        myImageHeight.setData(height);
    }
}
