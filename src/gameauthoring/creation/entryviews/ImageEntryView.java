package gameauthoring.creation.entryviews;

import java.io.File;
import java.net.MalformedURLException;
import gameauthoring.util.ErrorMessage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Button myChooseImage;
    private ImageView myImage;
    private StringProperty imagePath = new SimpleStringProperty("images/Square.png");
    private String buttonLabel = "Choose Image"; // TODO: Resource file

    public ImageEntryView (String label, String cssClass) {
        super(label, cssClass);
        initFileChooser(new FileChooser());
    }

    public ImageEntryView (String label, 
                           double width,
                           double height,
                           String cssClass) {
        this(label, cssClass);
        initImageView(new SimpleDoubleProperty(width), new SimpleDoubleProperty(height));
        init();
    }

    public ImageEntryView (String label, 
                           DoubleProperty width,
                           DoubleProperty height,
                           String cssClass) {
        this(label, cssClass);
        initImageView(width, height);
        init();

    }

    @Override
    protected void init () {
        getMyContainer().getChildren().addAll(myChooseImage, myImage);
    }

    private void initImageView (DoubleProperty width, DoubleProperty height) {
        myImage = getMyFactory().makeImageView(imagePath.get(), width, height);
    }

    public void updateImage (String url) {
        imagePath.set(url);
        myImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(imagePath.get())));
    }
    
    public String getImageURL () {
        return imagePath.get();
    }

    private void initFileChooser (FileChooser imageChoice) {
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        myChooseImage = getMyFactory().createButton(buttonLabel, e -> openImageChoice(imageChoice));
    }

    private void openImageChoice (FileChooser imageChoice) {
        File file = imageChoice.showOpenDialog(null);
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                updateImage(fileName);
            }
            catch (MalformedURLException e) {
                ErrorMessage err = new ErrorMessage("BAD URL");
                err.showError();
            }

        }
    }
    
    public void bindData (StringProperty url) {
        imagePath.bindBidirectional(url);
    }

    @Override
    public Node draw () {
        return getMyContainer();
    }

}
