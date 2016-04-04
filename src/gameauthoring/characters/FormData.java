package gameauthoring.characters;

import gameauthoring.Glyph;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Object representation of a form entry to make data collection
 * very simple. Every part of the form has a label for easy find
 * access and a string representation of the data. This would work
 * for text boxes, combo boxes, and other forms of entry.
 * @author Tommy
 *
 */
public abstract class FormData implements Glyph{

    private static final int SPACING = 5;
    
    private String myTitle;
    private HBox myContainer;
    
    public FormData (String label){
        myTitle = label;
        myContainer = createContainer();
    }
    
    private HBox createContainer () {
        HBox container = new HBox(SPACING);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.getChildren().add(new Text(myTitle));
        return container;
    }

    public Node draw (){
        return myContainer;
    }
    
    public String getLabel (){
        return myTitle;
    }
    
    public void setField (Node field){
        myContainer.getChildren().add(field);
    }
    
    public abstract String getData ();
}
