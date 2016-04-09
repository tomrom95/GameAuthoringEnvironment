package gameauthoring.levels.sprites;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public interface Draggable{
    
    default void setActions(Node source) {
        source.setOnDragDetected(e -> setOnDragDetected(e, source));
    }
    
    default ClipboardContent createClipboard(String name) {
        ClipboardContent content = new ClipboardContent();
        content.putString(name);
        return content;
    }
    
    public void setOnDragDetected(MouseEvent e, Node node);
    
    public void setOnDragOver(DragEvent e);
    
    public void setOnDragDropped(DragEvent e);

}
