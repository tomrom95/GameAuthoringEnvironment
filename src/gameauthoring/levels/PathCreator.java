package gameauthoring.levels;

import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import util.Coordinate;
import util.ScaleRatio;


/**
 * Controller to help create a path on the screen. Uses the JavaFX
 * path object. Basically works like how you define a polygon in
 * a simple paint application.
 * @author Tommy
 *
 */
public class PathCreator {
    
    private boolean makingPath = false;
    private Path myPath;
    private ScaleRatio myScale;

    /**
     * Begin a new path with a start point and a pane that the
     * mouse moves around in
     * @param startPoint
     * @param container
     */
    public void newPath (Coordinate startPoint, Pane container, ScaleRatio scale) {
        if(makingPath) return;
        myScale = scale;
        container.getChildren().remove(myPath);
        makingPath = true;
        container.requestFocus(); 
        myPath = new Path();
        container.getChildren().add(myPath);
        addToPath(startPoint, container);
    }
    
    /**
     * Ends the current path if one is being created
     * @param container
     */
    public void endPath (Pane container) {
        if (!makingPath) return;
        
        makingPath = false;
        container.setOnMouseMoved(null);
        int last = myPath.getElements().size() - 1;
        if(myPath.getElements().get(last) instanceof LineTo){
            myPath.getElements().remove(last);
        }
    }
    
    /**
     * Has the line follow the mouse whereever it goes
     * @param event
     * @param line
     */
    private void followMouse (MouseEvent event, LineTo line) {
        line.setX(event.getX());
        line.setY(event.getY());
    }
    
    /**
     * Adds new point to path from external mouse event
     * @param e
     * @param container
     */
    public void addToPath (MouseEvent e, Pane container) {
        Coordinate point = new Coordinate(e.getX(), e.getY());
        addToPath(point, container);
    }

    /**
     * Overloads previous class to add a coordinate to the current
     * path
     * @param point
     * @param container
     */
    public void addToPath (Coordinate point, Pane container) {
        if(!makingPath) return;
        
        container.requestFocus();
        myPath.getElements().add(new MoveTo(point.getX(), point.getY()));
        LineTo line = new LineTo(point.getX(), point.getY());
        myPath.getElements().add(line);
        container.setOnMouseMoved(e -> followMouse(e, line));
    }
    
    /**
     * Gets the path points of the currently created path
     * @return
     */
    public List<Coordinate> getPathPoints(){
        if (myPath == null) {
            return null;
        }
        return myPath.getElements()
                     .stream()
                     .filter(elem -> (elem instanceof MoveTo) )
                     .map(elem -> (MoveTo) elem)
                     .map(elem -> new Coordinate(myScale.invert(elem.getX()), myScale.invert(elem.getY())))
                     .collect(Collectors.toList());
    }
   

    public boolean isCreatingPath () {
        return makingPath;
    }
}
