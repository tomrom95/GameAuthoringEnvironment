package gameauthoring.creation.subforms.fire;

import engine.IGame;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.util.UIFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FiringSFVmult extends SubFormView {
    private GridPane myContainer;
    private UIFactory myUIFactory = new UIFactory();
    private ScrollPane myPane;
    private VBox myPaneContent;
    private HBox buttonHolder;

    
    public FiringSFVmult(IGame game, FiringSFCmult SFC){    
        Button dir = myUIFactory.createButton("direc", e->SFC.addSFC(new DirectionalFireSFC(game, SFC)));
        Button track = myUIFactory.createButton("track", e->SFC.addSFC(new TrackingFireSFC(game,SFC)));
        buttonHolder = myUIFactory.makeHBox(10, dir,track);
        initView();
    }
    

    @Override
    protected void initView () {
        myContainer = new GridPane();
        myPaneContent = myUIFactory.makeVBox(10, (Node[]) null);
        myPane = myUIFactory.makeScrollPane(myPaneContent, 300, 300); //TODO: magic number
        myContainer.add(buttonHolder, 0, 0);
        myContainer.add(myPane, 0, 1);
        
    }
    
    @Override
    public Node draw () {
        return myContainer;
    }

    public void addSFV (ISubFormView subFormView) {
        myPaneContent.getChildren().add(subFormView.draw());
    }

    public void removeSFV (ISubFormView subFormView) {
        myPaneContent.getChildren().remove(subFormView.draw());
    }




}
