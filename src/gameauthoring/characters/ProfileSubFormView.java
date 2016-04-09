package gameauthoring.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ProfileSubFormView extends SubFormView{

    private GridPane myPane = new GridPane();
    private IEntryView myName = new TextEntryView("Name: ", 20, 10, 40, false); // maybe change these
                                                                               // constructor
                                                                               // Arguments to
                                                                               // getters/setters
    private IEntryView myImage = new ImageEntryView("Image: ", 20, 20, 20);
    private IEntryView myDescription = new TextEntryView("Description: ", 20, 40, 60, false);
    private String myNameKey = "name";
    private String myDescriptionKey = "desctiption";
    private String myImageKey = "image";
    
    private List<IEntryView> myEntryViews = new ArrayList<IEntryView>(Arrays.asList(myName,myImage,myDescription));
    

    public ProfileSubFormView () {
        initView();
        initData();
        //getData()
    }


    private void initView () {
        super.setMyEntryViews(myEntryViews);
        myPane.add(myName.draw(), 0, 0);
        myPane.add(myImage.draw(), 0, 1);
        myPane.add(myDescription.draw(), 1, 0, 1, 1);
    }
    private void initData(){
        this.getData().add();
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
