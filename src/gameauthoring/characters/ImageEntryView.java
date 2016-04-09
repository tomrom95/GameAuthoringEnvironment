package gameauthoring.characters;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class ImageEntryView extends EntryView {
    private String myLabel;
    private HBox myContainer;
    private StringProperty myImageChoice = new SimpleStringProperty();// TODO Add default Image
    private Button myChooseImage = new Button("Choose Image");
    private ImageView myImage;
    private double width;
    private double height;

    public ImageEntryView (String label, IFormDataManager data, double spacing, double width, double height) {
        super(label,data);
        this.width = width;
        this.height = height;
        this.myContainer = new HBox(spacing);
        this.myImageChoice.bindBidirectional(getData().getValueProperty());
        initFileChooser(new FileChooser());   
        initImageView();
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChooseImage);
        myContainer.getChildren().add(myImage);        
    }

    private void initImageView () {        
        myImage = new ImageView(new Image(myImageChoice.get())); //TODO add default image
        myImageChoice.addListener(c->{myImage.setImage(new Image(myImageChoice.get()));});
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
