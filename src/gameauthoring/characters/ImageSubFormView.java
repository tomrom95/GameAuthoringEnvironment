package gameauthoring.characters;

import java.io.File;
import java.net.MalformedURLException;
import engine.ISprite;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class ImageSubFormView implements SubFormView {
    private SubFormController myController;
    private String myLabel;
    private HBox myContainer = new HBox(20);
    private String myImageChoice;
    private Button myChooseImage = new Button("Choose Image");

    public ImageSubFormView (String label, SubFormController controller) {
        this.myController = controller;
        this.myLabel = label;
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChooseImage);
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
        return null;
    }

    @Override
    public void populateWithData (ISprite s) {
        // TODO Auto-generated method stub

    }

    @Override
    public SubFormController getSubFormController () {
        return this.myController;
    }

    @Override
    public void setSubFormController (SubFormController controller) {
        this.myController = controller;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
