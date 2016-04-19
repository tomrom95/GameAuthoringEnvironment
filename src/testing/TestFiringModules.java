package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Game;
import engine.SpriteGroup;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.modules.Firer;
import engine.profile.Profile;
import engine.rendering.GameGridConfigNonScaling;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import gameplayer.GamePlayer;
import junit.framework.*;
import util.Coordinate;
import util.TimeDuration;

//TODO : remove class
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
        game = new Game(new GameGridConfigNonScaling(GamePlayer.PREFWIDTH, GamePlayer.PREFHEIGHT));
        
        projectile = new SpriteDefinition();
        mover = new ConstantMoverDefinition();
        mover.setSpeed(100);
        System.out.println(mover.getSpeed());
        mover.setXVel(10);
        mover.setYVel(10);
        projectile.setMovementDefinition(mover);
        System.out.println(projectile.getMovementDefinition().getSpeed());
        
        shooter = new SpriteDefinition();
        shooter.setLocation(new Coordinate(0,0));
        
        fire = new DirectionalFirerDefinition();
        fire.setProjectileDefinition(projectile);
        fire.setWaitTime(1000);
        fire.setGame(game);
        fire.setAngle(-60);
       
          
        enemy = new SpriteDefinition();
        enemy.setLocation(new Coordinate(100,100));
        Profile testprofile = new Profile("Test");
        enemy.setProfile(testprofile);
        
        List<SpriteDefinition> myTargets = new ArrayList<SpriteDefinition>();
        myTargets.add(enemy);
        
        trackingFire = new TrackingFirerDefinition();
        trackingFire.setGame(game);
        trackingFire.setProjectileDefinition(projectile);
        trackingFire.setWaitTime(100);
        SpriteGroup myGroup = new SpriteGroup(myTargets);
        
        trackingFire.setTargets(myGroup);
        
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
