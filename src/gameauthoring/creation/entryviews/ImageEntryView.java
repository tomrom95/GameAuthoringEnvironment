package gameauthoring.creation.entryviews;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import splash.LocaleManager;
import gameauthoring.util.ErrorMessage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import gameauthoring.util.BasicUIFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private ResourceBundle myLabel;

    public ImageEntryView (String label, IFormDataManager data, String cssClass) {
        super(label, data);
        this.myImageChoice.bindBidirectional(getData().getValueProperty());
        setResourceBundle();
        initFileChooser(new FileChooser());
    }

    private void setResourceBundle () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager.getInstance().getCurrentLocaleProperty().get());  
    }

    public ImageEntryView (String label,
                           IFormDataManager data,
                           double width,
                           double height,
                           String cssClass) {
        this(label, data, cssClass);
        initImageView(new SimpleDoubleProperty(width), new SimpleDoubleProperty(height));
        init(label, cssClass);
    }

    public ImageEntryView (String label,
                           IFormDataManager data,                           
                           DoubleProperty width,
                           DoubleProperty height,String cssClass) {
        this(label, data, cssClass);
        initImageView(width, height);
        init(label, cssClass);

    }

    @Override
    protected void init (String label, String cssClass) {
        this.myContainer = new GridPane();
        myContainer.add(getLabel(), 0, 0);
        myContainer.add(myChooseImage, 0, 1);
        myContainer.add(myImage, 0, 2);
        myContainer.getStyleClass().add(cssClass);
    }

    private void initImageView (DoubleProperty width, DoubleProperty height) {
        myImage = myUIFactory.makeImageView(imagePath, width, height);
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
        imageChoice.setTitle(myLabel.getString("Image"));
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter(myLabel.getString("ImageFile"), "*.png", "*.jpg", "*.gif")); //TODO: Default resourcebundle
        myChooseImage = myUIFactory.createButton(myLabel.getString("Image"), e -> openImageChoice(imageChoice));
        
    }

    private void openImageChoice (FileChooser imageChoice) {
        File file = imageChoice.showOpenDialog(null);
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                myImageChoice.setValue(fileName);
            }
            catch (MalformedURLException e) {
                ErrorMessage err = new ErrorMessage(myLabel.getString("BadURL"));
                err.showError();
            }

        }
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
