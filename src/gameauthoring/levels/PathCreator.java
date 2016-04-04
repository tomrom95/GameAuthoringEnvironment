package gameauthoring.levels;

import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import util.Coordinate;

public class PathCreator {
    
    private boolean makingPath = false;
    private Path myPath;

    public void newPath (Coordinate startPoint, Pane container) {
        if(makingPath) return;
        container.getChildren().remove(myPath);
        makingPath = true;
        container.requestFocus();
        
        myPath = new Path();
        container.getChildren().add(myPath);
        addToPath(startPoint, container);
    }
    
    public void endPath (Pane container) {
        if (!makingPath) return;
        
        makingPath = false;
        container.setOnMouseMoved(null);
        int last = myPath.getElements().size() - 1;
        if(myPath.getElements().get(last) instanceof LineTo){
            myPath.getElements().remove(last);
        }
    }
    
    private void followMouse (MouseEvent event, LineTo line) {
        line.setX(event.getX());
        line.setY(event.getY());
    }
    
    public void addToPath (MouseEvent e, Pane container) {
        Coordinate point = new Coordinate(e.getX(), e.getY());
        addToPath(point, container);
    }

    public void addToPath (Coordinate point, Pane container) {
        if(!makingPath) return;
        
        container.requestFocus();
        myPath.getElements().add(new MoveTo(point.getX(), point.getY()));
        LineTo line = new LineTo(point.getX(), point.getY());
        myPath.getElements().add(line);
        container.setOnMouseMoved(e -> followMouse(e, line));
    }
    
    public List<Coordinate> getPathPoints(){
        if (myPath == null) {
            return null;
        }
        return myPath.getElements()
                     .stream()
                     .filter(elem -> (elem instanceof MoveTo) )
                     .map(elem -> (MoveTo) elem)
                     .map(elem -> new Coordinate(elem.getX(), elem.getY()))
                     .collect(Collectors.toList());
    }
    
    public boolean isCreatingPath () {
        return makingPath;
    }
}
