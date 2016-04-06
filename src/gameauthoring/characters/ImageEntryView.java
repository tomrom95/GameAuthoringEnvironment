package gameauthoring.characters;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class ImageEntryView implements EntryView {
    private String myLabel;
    private HBox myContainer = new HBox(20);
    private String myImageChoice;
    private Button myChooseImage = new Button("Choose Image");
    private ImageView myImage;

    public ImageEntryView (String label) {
        this.myLabel = label;
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChooseImage);
        myContainer.getChildren().add(myImage);
        initFileChooser(new FileChooser());        
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
                myImageChoice = fileName;
                myImage = new ImageView(new Image(fileName));
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
    public FormData getData () {
        String key = myLabel;
        String value = myImageChoice;
        return new FormData(key, new ArrayList<String>(Arrays.asList(value)));
    }

    @Override
    public void populateWithData (FormData data) {
        this.myImage.setImage(new Image(data.getMyValue().get(0)));
    }
   

    @Override
    public Node draw () {
        return myContainer;
    }

}
