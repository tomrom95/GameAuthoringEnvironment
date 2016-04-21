package engine.aipathing;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.IGame;
import engine.ISpriteGroup;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.Mover;
import util.BitMap;
import util.Coordinate;
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
    private ISpriteGroup myGoalGroup;
    private IOrientationFinder myRotationStrategy;

    public GoalBasedMover (Positionable positionable,
                           IGame game,
                           ISpriteGroup goalGroup,
                           boolean shouldRotate) {
        super(positionable);
        myPather = new AStarPather();
        myGame = game;
        myGoalGroup = goalGroup;
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
                furthestReachablePoint(goalPath, distance(getSpeed(), durationToDouble(duration)));
        getParent().setLocation(targetCoordinate);
        getParent().setOrientation(myRotationStrategy.angleFromCoordinates(getLocation(),
                                                                           targetCoordinate));
    }

    private Coordinate furthestReachablePoint (List<Coordinate> waypoints,
                                               double distanceTravelable) {
        // TODO finish this method
        return null;
    }

    private Coordinate findNearestGoal () {
        // TODO finish this method
        return null;
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

    private BitMap obstructionMap () {
        return getGame().getObstructionManager().getObstructionMap();
    }

    private IGame getGame () {
        return myGame;
    }

}
