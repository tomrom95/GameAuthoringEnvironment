package util;

import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public interface Draggable{
    
    default void setActions(Node source) {
        source.setOnDragDetected(e -> setOnDragDetected(e, source));
    }
    
    public void setOnDragDetected(MouseEvent e, Node node);
    
    public void setOnDragOver(DragEvent e);
    
    public void setOnDragDropped(DragEvent e);

}
