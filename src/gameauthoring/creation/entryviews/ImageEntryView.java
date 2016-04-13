package gameauthoring.creation.entryviews;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import gameauthoring.util.ErrorMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class ImageEntryView extends EntryView {
    private GridPane myContainer;
    private StringProperty myImageChoice = new SimpleStringProperty();// TODO Add default Image
    private Button myChooseImage = new Button("Choose Image");
    private ImageView myImage;
    private String imagePath = "images/Square.png";


    public ImageEntryView (String label, IFormDataManager data, double width, double height, String cssClass) {
        super(label,data);
        this.myContainer = new GridPane(); 
        this.myImageChoice.bindBidirectional(getData().getValueProperty());
        initFileChooser(new FileChooser());   
        initImageView(width,height);
        myContainer.add(new Label(myLabel), 0, 0);
        myContainer.add(myChooseImage, 0, 1);
        myContainer.add(myImage, 0, 2);        
        myContainer.getStyleClass().add(cssClass);
    }

    private void initImageView (double width, double height) {        
        myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath))); //TODO add default image
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
        myImageChoice.addListener(c->updateImage());
    }

    private void updateImage () {
        if(myImageChoice.get()!=""){
            myImage.setImage(new Image(myImageChoice.get()));
        }
        else{
            myImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(imagePath)));
        }
    }

    private void initFileChooser (FileChooser imageChoice) {
        imageChoice.setTitle("Image Choice");
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        myChooseImage.setOnAction(e -> openImageChoice(imageChoice));
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
    public void update () {
        // TODO Auto-generated method stub

    }
   

    @Override
    public Node draw () {
        return myContainer;
    }

}
