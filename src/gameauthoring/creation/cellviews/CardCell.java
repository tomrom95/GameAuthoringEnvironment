package gameauthoring.creation.cellviews;

import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.UIFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class CardCell extends ListCell<String> {

    private UIFactory myFactory= new BasicUIFactory();
    private double myHeight;
   
    
    public CardCell (double height) {
        myHeight = height;
    }
    
    @Override
    protected void updateItem (String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        }
        else {
            setGraphic(createHBox(item));
        }
    }

    private Node createHBox (String item) {
       HBox hbox = new HBox();
       hbox.setAlignment(Pos.CENTER);
       hbox.getChildren().add(myFactory.createSubTitleLabel(item));
       hbox.setPrefHeight(myHeight);
       return hbox;
    }

}
