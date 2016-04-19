package engine.modules;

import java.util.Comparator;
import java.util.List;
import engine.sprite.ISprite;
import util.Coordinate;


/**
 * This class serves to locate and alter the orientation of objects based on the proximity of other
 * sprites.
 * <p>
 * This class uses a comparator to query available sprites on the level and located the nearest one.
 *
 * @author Timko
 *         
 */
public class EnemyTracker {

	
    /**
     * Locate the distance of the closest sprite using a comparator based on manhattan distance
     *
     * @param enemies sprites available on the level
     * @param myLocation Coordinate of the sprite
     * @return the closest sprite
     */
	
    private ISprite getClosestEnemy (List<ISprite> enemies, Coordinate myLocation) {
        Comparator<ISprite> myDistanceComparator =
                (e1, e2) -> Double.compare(calculateDistance(myLocation, e1.getLocation()),
                                           calculateDistance(myLocation, e2.getLocation()));
        return enemies.stream().min(myDistanceComparator).get();

    }
    
     

    private double calculateDistance (Coordinate myLocation, Coordinate enemyLocation) {

        return Math.sqrt(Math.pow((myLocation.getX() - enemyLocation.getX()), 2) +
                         Math.pow((myLocation.getY() - enemyLocation.getY()), 2));

    }

    protected double calculateXVelToClosestEnemy (Coordinate myLocation,
                                                  List<ISprite> enemies,
                                                  double speed) {
        ISprite closestEnemy = getClosestEnemy(enemies, myLocation);
        double newAngle =
                calculateAbsoluteOrientationToEnemy(myLocation, closestEnemy.getLocation());
        return Math.cos(newAngle) * speed;
    }

    protected double calculateYVelToClosestEnemy (Coordinate myLocation,
                                                  List<ISprite> enemies,
                                                  double speed) {
        ISprite closestEnemy = getClosestEnemy(enemies, myLocation);
        double newAngle =
                calculateAbsoluteOrientationToEnemy(myLocation, closestEnemy.getLocation());
        return Math.sin(newAngle) * speed;
    }

    /**
     * Computes the angle between the orientation of the caller and where the closest enemy is
     * positioned
     *
     * @param myLocation Coordinate of caller position
     * @param enemyLocation Coordinate of enemy position
     * @return value representing orientation differences
     */
    private double calculateAbsoluteOrientationToEnemy (Coordinate myLocation,
                                                        Coordinate enemyLocation) {
    	
    	//TODO: make this method a bit cleaner
        double xDelta = enemyLocation.getX() - myLocation.getX();
        double yDelta = enemyLocation.getY() - myLocation.getY();
        double arctangent = Math.atan(yDelta / xDelta) * 180 / Math.PI;
        boolean enemyIsToRight = xDelta >= 0;
        boolean enemyIsAbove = yDelta >= 0;
        int quadrant = getQuadrant(enemyIsToRight, enemyIsAbove);
        double angleToEnemy;
        switch (quadrant) {
            case 1:
                angleToEnemy = 0 + arctangent;
                break;
            case 4:
                angleToEnemy = 360 + arctangent;
                break;
            default:
                angleToEnemy = 180 + arctangent;
        }

        double backToRads = angleToEnemy * Math.PI / 180;
        return backToRads;

    }

    private int getQuadrant (boolean right, boolean above) {
        return right ? (above ? 1 : 4) : (above ? 2 : 3);

    }
}
