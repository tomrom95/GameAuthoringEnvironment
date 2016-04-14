package testing;

import engine.Game;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.LocationDefinition;
import engine.definitions.SpriteDefinition;
import engine.modules.Firer;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import junit.framework.*;
import util.Coordinate;
import util.TimeDuration;


public class TestFiringModules extends TestCase {
    
    private SpriteDefinition projectile;
    private ConstantMoverDefinition mover;
    private SpriteDefinition shooter;
    private DirectionalFirerDefinition fire;
    private Game game;
    private SpriteDefinition enemy;
    

    // assigning the values
    @Override
    protected void setUp(){
        game = new Game();
        
        projectile = new SpriteDefinition();
        mover = new ConstantMoverDefinition();
        mover.setSpeed(100);
        System.out.println(mover.getSpeed());
        mover.setXVel(10);
        mover.setYVel(10);
        projectile.setMovementDefinition(mover);
        System.out.println(projectile.getMovementDefinition().getSpeed());
        
        shooter = new SpriteDefinition();
        LocationDefinition myLocation = new LocationDefinition();
        myLocation.setX(0);
        myLocation.setY(0);
        shooter.setLocation(myLocation);
        
        fire = new DirectionalFirerDefinition();
        fire.setProjectileDefinition(projectile);
        fire.setWaitTime(1000);
        fire.setGame(game);
        fire.setAngle(-60);
       
        shooter.addModule(fire);
        
        
        
        
        enemy = new SpriteDefinition();
        LocationDefinition enemyLocation = new LocationDefinition();
        enemyLocation.setX(100);
        enemyLocation.setY(100);
        enemy.setLocation(enemyLocation);
        
        
      
        
    }

    public  void testFire(){
      double speed =  projectile.getMovementDefinition().getSpeed();
      System.out.println("testFire speed: "+ speed);
              
      ISprite shooterGuy = shooter.create();
      
      shooterGuy.update(new TimeDuration(1000000));
      shooterGuy.update(new TimeDuration(1));
//      System.out.println(game.getLevelManager().getCurrentLevel().getSprites().size());
//      assertEquals(1, game.getLevelManager().getCurrentLevel().getSprites().size());
      assertEquals(-60 , game.getLevelManager().getCurrentLevel().getSprites().get(0).getMovementStrategy().getAngle());
    }
    
   

}
