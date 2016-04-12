package engine.modules;

import java.util.Comparator;
import java.util.List;
import engine.sprite.ISprite;
import util.Coordinate;

public class EnemyTracker {

    protected ISprite getClosestEnemy(List<ISprite> enemies, Coordinate myLocation){
        final Comparator<ISprite> closer = (e1,e2) -> Double.compare(calculateDistance(myLocation,e1.getLocation()),
                                                                     calculateDistance(myLocation, e2.getLocation()));
        return enemies.stream().min(closer).get();
         
    }
    
    
    protected double getOrientationToClosestEnemy(List<ISprite> enemies, Coordinate myLocation){
        ISprite closestEnemy = getClosestEnemy(enemies, myLocation);
        return calculateAbsoluteOrientationToEnemy(myLocation, closestEnemy.getLocation());  
    }
    
    
    
    private double calculateDistance(Coordinate myLocation, Coordinate enemyLocation){
        
      return Math.sqrt(Math.pow((myLocation.getX() - enemyLocation.getX()), 2) + Math.pow((myLocation.getY() - enemyLocation.getY()), 2)); 
        
    }
    
    protected double calculateAbsoluteOrientationToEnemy(Coordinate myLocation, Coordinate enemyLocation){
        double xDelta = enemyLocation.getX() - myLocation.getX();
        double yDelta = enemyLocation.getY() - myLocation.getY();
        double arctangent = Math.atan(yDelta/xDelta) * 180 / Math.PI; 
        boolean enemyIsToRight = xDelta >= 0;
        boolean enemyIsAbove = yDelta >= 0;
        int quadrant = getQuadrant(enemyIsToRight, enemyIsAbove);
        double angleToEnemy;
        switch(quadrant){
            case 1:
                angleToEnemy = 0 + arctangent;
                break;
            case 4:
                angleToEnemy = 360 + arctangent;
                break;
            default:
                angleToEnemy = 180 + arctangent;
        }
        
        return angleToEnemy;
        
    }
    
    private int getQuadrant(boolean right, boolean above){
        if(right){
            if(above){
                return 1;
            } else{
                return 4;
            }
        } else{
            if(above){
                return 2;
            } else{
                return 3;
            }
        }
        
    }
}
