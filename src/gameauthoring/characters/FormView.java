package gameauthoring.characters;

import java.util.*;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class FormView implements IFormView {

    private VBox mySubFormContainer = new VBox();
    private ScrollPane mySubFormViewer = new ScrollPane(mySubFormContainer);
    private GridPane myFormView = new GridPane();
    private Button mySaveButton = new Button("Save");
    private Button myDeleteButton = new Button("Delete");
    private List<Node> myButtons = new ArrayList<Node>(Arrays.asList(mySaveButton,myDeleteButton));
    private List<EntryView> mySubFormViews;

    

    public FormView(List<EntryView> subFormViews){
        mySubFormViews = subFormViews;
        mySubFormViewer.setPrefHeight(500); //TODO Magic Number
        myFormView.add(mySubFormViewer, 0, 0); 
        myFormView.add(createButtonHolder(), 0 , 1);
        generateView(mySubFormViews);            
    }
    
    private Node createButtonHolder () {
        HBox buttonHolder = new HBox(10);//TODO Magic Number
        buttonHolder.getChildren().addAll(myButtons);
        return buttonHolder;
    }

    private void generateView (List<EntryView> subFormViews) {
        for(EntryView s:subFormViews){
            mySubFormContainer.getChildren().add(s.draw());
        }
    }

    @Override
    public void setSaveAction (Consumer<?> action) {
        mySaveButton.setOnAction(e->action.accept(null));
    }

    @Override
    public void setDeleteAction (Consumer<?> action) {
        myDeleteButton.setOnAction(e->action.accept(null));
    }

    @Override
    public List<EntryView> getSubFormView () {
        return mySubFormViews;
    }

    @Override
    public Node draw () {
        return myFormView;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
