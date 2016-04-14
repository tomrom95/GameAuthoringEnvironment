package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Game;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.LocationDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.modules.Firer;
import engine.profile.Profile;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import junit.framework.*;
import util.Coordinate;
import util.TimeDuration;


public class TestFiringModules extends TestCase {
    
    private SpriteDefinition projectile;
    private ConstantMoverDefinition mover;
    private SpriteDefinition shooter;
    private DirectionalFirerDefinition fire;
    private TrackingFirerDefinition trackingFire;
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
       
//        shooter.addModule(fire);
        
        
        
        
        enemy = new SpriteDefinition();
        LocationDefinition enemyLocation = new LocationDefinition();
        enemyLocation.setX(100);
        enemyLocation.setY(100);
        enemy.setLocation(enemyLocation);
        Profile testprofile = new Profile("Test");
        enemy.setProfile(testprofile);
        
        List<SpriteType> myTargets = new ArrayList<SpriteType>();
        myTargets.add(enemy.getSpriteType());
        
        trackingFire = new TrackingFirerDefinition();
        trackingFire.setGame(game);
        trackingFire.setProjectileDefinition(projectile);
        trackingFire.setWaitTime(100);
        trackingFire.setTargets(myTargets);
        
        shooter.addModule(trackingFire);
        
        
        
        
      
        
    }
    
  
    
    
    public  void testFire(){
      double speed =  projectile.getMovementDefinition().getSpeed();
      System.out.println("testFire speed: "+ speed);
              
      ISprite shooterGuy = shooter.create();
      
      game.add(shooterGuy);
      game.add(enemy.create());
      game.update(new TimeDuration(100000));
      game.update(new TimeDuration(200000));
//      shooterGuy.update(new TimeDuration(1000000));
//      shooterGuy.update(new TimeDuration(1));
//      System.out.println(game.getLevelManager().getCurrentLevel().getSprites().size());
      assertEquals(3, game.getLevelManager().getCurrentLevel().getSprites().size());
//      assertEquals(-60 , game.getLevelManager().getCurrentLevel().getSprites().get(0).getMovementStrategy().getAngle());
      
    }
    
   

}
