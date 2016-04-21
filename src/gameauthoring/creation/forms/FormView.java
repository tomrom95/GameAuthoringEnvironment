package gameauthoring.creation.forms;

import java.util.*;
import java.util.function.Consumer;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Joe Lilien
 *
 */
public class FormView implements IFormView {

    private VBox mySubFormContainer = new VBox();
    private ScrollPane mySubFormViewer = new ScrollPane(mySubFormContainer);
    private GridPane myFormView = new GridPane();
    private Button mySaveButton = new Button("Save");
    private Button myDeleteButton = new Button("Delete");
    private Button myNewButton = new Button("New");
    private List<Node> myButtons = new ArrayList<Node>(Arrays.asList(mySaveButton,myDeleteButton, myNewButton));
    private List<ISubFormView> mySubFormViews;
    private static final double HEIGHT = 525; //TODO: move to common resource file    
    

    public FormView(List<ISubFormView> subFormViews){
        mySubFormViews = subFormViews;
        mySubFormViewer.getStyleClass().add("myFormView");
        mySubFormViewer.setMaxHeight(HEIGHT);
        myFormView.add(mySubFormViewer, 0, 1); 
        myFormView.add(createButtonHolder(), 0 , 0);
        setViews(mySubFormViews);            
    }
    
    private Node createButtonHolder () {
        HBox buttonHolder = new HBox(10);//TODO Magic Number
        buttonHolder.getChildren().addAll(myButtons);
        return buttonHolder;
    }
    
    /**
     * New Design, change active subform views
     */
    public void setViews(List<ISubFormView> subFormViews){
        mySubFormContainer.getChildren().setAll(getSFVNodes(subFormViews));
    }
    
    private List<Node> getSFVNodes(List<ISubFormView> subFormViews){
        List<Node> nodes = new ArrayList<>();
        subFormViews.forEach(e->nodes.add(e.draw()));       
        return nodes;
    }
    
    /**
     * Define save action for button
     */
    @Override
    public void setSaveAction (Consumer<?> action) {
        mySaveButton.setOnAction(e->action.accept(null)); //Not sure what the input should be here
    }

    /**
     * Define delete action for button
     */
    @Override
    public void setDeleteAction (Consumer<?> action) {
        myDeleteButton.setOnAction(e->action.accept(null)); //Not sure what the input should be here
    }

    /**
     * Define new action for button
     */
    @Override
    public void setNewAction(Consumer<?> action){
        myNewButton.setOnAction(e->action.accept(null));
    }
    
    @Override
    public List<ISubFormView> getSubFormViews () {
        return mySubFormViews;
    }

    @Override
    public Node draw () {
        return myFormView;
    }
}
