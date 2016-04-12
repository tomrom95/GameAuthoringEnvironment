package engine.modules;

import java.util.List;
import engine.sprite.ISprite;
import util.Coordinate;

public class EnemyTracker {

    protected ISprite getClosestEnemy(List<ISprite> enemies, Coordinate myLocation){
        
        
        return null;
        
    }
    
    
    protected ISprite getOrientationToClosestEnemy(List<ISprite> enemies, Coordinate myLocation){
        return null;
        
    }
    
    
    private double calculateDistance(Coordinate myLocation, Coordinate enemyLocation){
        
      return Math.sqrt(Math.pow((myLocation.getX() - enemyLocation.getX()), 2) + Math.pow((myLocation.getY() - enemyLocation.getY()), 2)); 
        
    }
    
    private double calculateOrientationChange(Coordinate myLocation, Coordinate enemyLocation){
        return ;
        
    }
}
