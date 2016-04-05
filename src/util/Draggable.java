package util;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public interface Draggable{
    
    default void setActions(Node source, Pane target) {
        source.setOnDragDetected(e -> setOnDragDetected(e));
    }
    
    public Image getDragImage();
    
    public void setOnDragDetected(MouseEvent e);
    
    public void setOnDragOver(DragEvent e);
    
    public void setOnDragDropped(DragEvent e);

}
