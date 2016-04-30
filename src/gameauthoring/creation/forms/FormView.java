package gameauthoring.creation.forms;

import java.util.*;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.BasicUIFactory;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import splash.LocaleManager;

/**
 * 
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class FormView implements IFormView {

    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", LocaleManager
                                                             .getInstance().getCurrentLocaleProperty().get());
    private String buttonClass = "CreationButton";
    private FlowPane mySubFormContainer = new FlowPane(Orientation.VERTICAL);
    private BasicUIFactory myFactory = new BasicUIFactory();
    private ScrollPane mySubFormViewer = new ScrollPane(mySubFormContainer);
    private GridPane myFormView = new GridPane();
    //TODO: add buttons to languages    
    private Button mySaveButton = myFactory.createStyledButton("Save", buttonClass);
    private Button myDeleteButton = myFactory.createStyledButton("Delete", buttonClass);
    private Button myNewButton = myFactory.createStyledButton("New", buttonClass);
    private List<Node> myButtons = new ArrayList<Node>(Arrays.asList(mySaveButton,myDeleteButton, myNewButton));
    private List<ISubFormView> mySubFormViews;
    private static final double HEIGHT = 525; //TODO: move to common resource file    
    private static final double WRAP_LENGTH = HEIGHT - 20;
    public FormView(List<ISubFormView> subFormViews){   
        mySubFormContainer.setPrefWrapLength(WRAP_LENGTH);
        mySubFormViews = subFormViews;
        myFactory.addStyling(mySubFormViewer, "FormView");
        mySubFormViewer.setMaxHeight(HEIGHT);
        myFormView.add(mySubFormViewer, 0, 1); 
        hideForm();
        myFormView.add(createButtonHolder(), 0 , 0);
        setViews(mySubFormViews);            
    }
    
    public void showForm(){
        //myFormView.add(mySubFormViewer,  0,  1);
        mySubFormViewer.setVisible(true);
        mySaveButton.setDisable(false);
        myDeleteButton.setDisable(false);

    }
    @Override
    public void hideForm () {
        //myFormView.getChildren().remove(mySubFormViewer);
        mySubFormViewer.setVisible(false);
        mySaveButton.setDisable(true);
        myDeleteButton.setDisable(true);
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
        subFormViews.forEach(e-> nodes.add(e.draw()));       
        return nodes;
    }
    
    /**
     * Define save action for button
     */
    @Override
    public void setSaveAction (Runnable action) {
        mySaveButton.setOnAction(e->action.run()); 
    }

    /**
     * Define delete action for button
     */
    @Override
    public void setDeleteAction (Runnable action) {
        myDeleteButton.setOnAction(e->action.run());
    }

    /**
     * Define new action for button
     */
    @Override
    public void setNewAction(Runnable action){
        myNewButton.setOnAction(e->action.run());
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
