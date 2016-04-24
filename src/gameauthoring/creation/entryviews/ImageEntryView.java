package gameauthoring.creation.entryviews;

import java.io.File;
import java.net.MalformedURLException;
import gameauthoring.util.ErrorMessage;
import gameauthoring.util.BasicUIFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * EntryView allows users to select and view an image to be associated with any object they are
 * creating
 * 
 * 
 * TODO: maybe move all the file chooser stuff to factory to avoid duplicate code
 * 
 * @author Joe Lilien
 *
 */
public class ImageEntryView extends EntryView {
    private GridPane myContainer;
    private StringProperty myImageChoice = new SimpleStringProperty();
    private Button myChooseImage;
    private ImageView myImage;
    private String imagePath = "images/Square.png";
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private String buttonLabel = "Choose Image"; // TODO: Resource file

    public ImageEntryView (String label,
                           IFormDataManager data,
                           double width,
                           double height,
                           String cssClass) {
        super(label, data);
        this.myImageChoice.bindBidirectional(getData().getValueProperty());
        initFileChooser(new FileChooser());
        initImageView(width, height);
        init(label, cssClass);


    }

    @Override
    protected void init (String label, String cssClass) {
        this.myContainer = new GridPane();
        myContainer.add(new Label(myLabel), 0, 0);
        myContainer.add(myChooseImage, 0, 1);
        myContainer.add(myImage, 0, 2);
        myContainer.getStyleClass().add(cssClass);
    }

    private void initImageView (double width, double height) {
        myImage = new ImageView(new Image(getClass().getClassLoader()
                .getResourceAsStream(imagePath)));
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
        myImageChoice.addListener(c -> updateImage());
    }

    private void updateImage () {
        if (myImageChoice.get() != "") {
            myImage.setImage(new Image(myImageChoice.get()));
        }
        else {
            myImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(imagePath)));
        }
    }

    private void initFileChooser (FileChooser imageChoice) {
        imageChoice.setTitle("Image Choice");
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        myChooseImage = myUIFactory.createButton(buttonLabel, e -> openImageChoice(imageChoice));
    }

    private void openImageChoice (FileChooser imageChoice) {
        File file = imageChoice.showOpenDialog(null);
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                myImageChoice.setValue(fileName);
            }
            catch (MalformedURLException e) {
                ErrorMessage err = new ErrorMessage("BAD URL");
                err.showError();
            }

        }
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
