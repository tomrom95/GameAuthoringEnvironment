package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.IAttribute;
import engine.IGame;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.Mover;
import engine.sprite.ISprite;
import util.Coordinate;
import util.ISampledBitMap;
import util.TimeDuration;


/**
 * This mover will use node based graph search and will simply try to move
 * as far as possible along the returned path each turn
 * 
 * @author jonathanim
 *
 */
public class GoalBasedMover extends Mover {
    
    private INodeGraphPather myPather;
    private IGame myGame;
    //private ISpriteGroup myGoalGroup;
    private IOrientationFinder myRotationStrategy;

    public GoalBasedMover (Positionable positionable,
                           IGame game,
                           boolean shouldRotate) {
        super(positionable);
        myPather = new AStarPather(game);
        myGame = game;
        //myGoalGroup = goalGroup;
        myRotationStrategy = getRotationStrategy(shouldRotate);
    }

    private IOrientationFinder getRotationStrategy (boolean shouldRotate) {
        return shouldRotate ? new InDirectionOfMovement() : new NullOrientation();
    }

    @Override
    public void update (TimeDuration duration) {
        Coordinate goal = findNearestGoal();
        List<Coordinate> goalPath = myPather.findPathFor(obstructionMap(), getLocation(), goal);        
        Coordinate targetCoordinate =
                furthestReachablePoint(getLocation(), goalPath,
                                       distance(getSpeed(), durationToDouble(duration)));
        getParent().setLocation(targetCoordinate);
        getParent().setOrientation(myRotationStrategy.angleFromCoordinates(getLocation(),
                                                                           targetCoordinate));
    }

    private Coordinate furthestReachablePoint (Coordinate start,
                                               List<Coordinate> waypoints,
                                               double distanceTravelable) {
        Coordinate curLoc = start;
        double distanceTravelled = 0;
        for (Coordinate next : waypoints) {
            if (distanceTravelled + Coordinate.distance(curLoc, next) <= distanceTravelable) {
                distanceTravelled += Coordinate.distance(curLoc, next);
                curLoc = next;
            }
            else {
                return linearInterp(curLoc, next, distanceTravelable - distanceTravelled);
            }

        }
        return curLoc;
    }


    /**
     * Will generate a coordinate starting from the first coordinate
     * in the direction of the second coordinate as far as distance
     * will allow
     * 
     * @param start coordinate
     * @param end coordinate
     * @param distance how far to move along vector from start to end coordinate
     * @return
     */
    private Coordinate linearInterp (Coordinate start, Coordinate end, double distance) {
        double deltaX = end.getX() - start.getX();
        double deltaY = end.getY() - start.getY();
        if (deltaX == 0 && deltaY == 0) {
            return start;
        }
        double normalizeDistanceConstant =
                Math.sqrt(Math.pow(distance, 2) / (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
        double proposedX = start.getX() + deltaX * normalizeDistanceConstant;
        double proposedY = start.getY() + deltaY * normalizeDistanceConstant;

        return new Coordinate(proposedX, proposedY);
    }

    /**
     * This method will pick the closest straight line distance
     * goal to try to path to, we could later if this framework
     * is not too slow chose instead to run the pather on each
     * possible goal and pick the one that is closest in actual distance
     * 
     * @return the coordinate
     */
    private Coordinate findNearestGoal () {
        List<ISprite> sprites = getGame().getLevelManager().getCurrentLevel().getSprites();
        Coordinate myLocation = getLocation();
        Coordinate closest = getLocation();
        double leastDistance = Double.MAX_VALUE;
        for (ISprite sprite : goalSprites(sprites)) {
            double consideredDistance = Coordinate.distance(sprite.getLocation(), myLocation);
            if (consideredDistance < leastDistance) {
                leastDistance = consideredDistance;
                closest = sprite.getLocation();
            }
        }
        return closest;
    }

    private List<ISprite> goalSprites (List<ISprite> spriteList) {
        return spriteList
                .stream()
                .filter(sprite -> sprite.getStatusModule().isGoal())
                .collect(Collectors.toList());
    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        return new ArrayList<>();
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing
    }

    private ISampledBitMap obstructionMap () {
        return getGame().getObstructionManager().getObstructionMap();
    }

    private IGame getGame () {
        return myGame;
    }

}
