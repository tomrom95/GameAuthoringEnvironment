package gameauthoring.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class ProfileSubFormView extends SubFormView{

    private GridPane myPane = new GridPane();
    private String myNameKey = "Name: ";
    private String myDescriptionKey = "Description: ";
    private String myImageKey = "Image: ";   
    
    private IEntryView myName = new TextEntryView(myNameKey,this.getData(), 20, 150, 30); // maybe change these
                                                                               // constructor
                                                                               // Arguments to
                                                                               // getters/setters
    private IEntryView myImage = new ImageEntryView(myImageKey,this.getData(), 20, 100, 100);
    private IEntryView myDescription = new TextEntryView(myDescriptionKey, this.getData(), 20, 150, 90);
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myName,myImage,myDescription));
    

    public ProfileSubFormView () {
        initView();
    }

    // Button for binding testing
    private Button testButton(){
        Button button = new Button("Test Map");
        button.setOnAction(e->{System.out.println(this.getData().getValueProperty(myNameKey));
                                System.out.println(this.getData().getValueProperty(myImageKey));});
        return button;
    }
    private Button testSave(){
        Button button = new Button("Update Map");
        button.setOnAction(e->{this.getData().set(myNameKey, "cool");});
        return button;
    }
    
    //
    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.setGridLinesVisible(true);
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myDescription.draw(), 0, 1);
        myPane.add(myImage.draw(), 1,0, 1, 1);    
        myPane.add(testButton(), 0, 2);
        myPane.add(testSave(), 1, 2);
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

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    

}
