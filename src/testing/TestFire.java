package testing;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import engine.Game;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.LocationDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import util.TimeDuration;


/**
 * Test package to test firing modules
 * 
 * @author Dhrumil
 *
 */

public class TestFire {

    private SpriteDefinition myProjectile;
    private ConstantMoverDefinition myMover;
    private SpriteDefinition myTower;
    private DirectionalFirerDefinition myDirectionalFirer;
    private TrackingFirerDefinition myTrackingFirer;
    private Game myGame;
    private SpriteDefinition myEnemy;

    @Before
    public void setUp () {
        myGame = new Game();

        createMover();
        createProjectile();
        createTower();
        createDirectionalFirer();
        createTrackingFirer();

        //myTower.addModule(myDirectionalFirer);
        myTower.addModule(myTrackingFirer);
        
        myEnemy = createEnemy();
    }

    private void createProjectile () {
        myProjectile = new SpriteDefinition();
        myProjectile.setMovementDefinition(myMover);
    }

    private SpriteDefinition createEnemy () {
        myEnemy = new SpriteDefinition();
        LocationDefinition enemyLocation = new LocationDefinition();
        enemyLocation.setX(100);
        enemyLocation.setY(100);
        myEnemy.setLocation(enemyLocation);
        return myEnemy;
    }

    private void createDirectionalFirer () {
        myDirectionalFirer = new DirectionalFirerDefinition();
        myDirectionalFirer.setProjectileDefinition(myProjectile);
        myDirectionalFirer.setWaitTime(1000);
        myDirectionalFirer.setGame(myGame);
        myDirectionalFirer.setAngle(120);
    }

    private void createTrackingFirer () {
        myTrackingFirer = new TrackingFirerDefinition();
        myTrackingFirer.setProjectileDefinition(myProjectile);
        myTrackingFirer.setWaitTime(1000);
        myTrackingFirer.setGame(myGame);
        List<SpriteType> enemy = new ArrayList<SpriteType>();
        enemy.add(createEnemy().create().getType());
        
        myTrackingFirer.setTargets(enemy);
    }

    private void createTower () {
        myTower = new SpriteDefinition();
        LocationDefinition myLocation = new LocationDefinition();
        myLocation.setX(0);
        myLocation.setY(0);
        myTower.setLocation(myLocation);
    }

    private void createMover () {
        myMover = new ConstantMoverDefinition();
        myMover.setSpeed(100);
        myMover.setXVel(10);
        myMover.setYVel(10);
    }

    @Test
    public void testBulletsCreation () {
        List<ISprite> towerList = new ArrayList<ISprite>();
        int bullets = 100;
        for (int i = 0; i < bullets; i++) {
            towerList.add(myTower.create());
        }

        towerList.stream().forEach(sprite -> sprite.update(new TimeDuration(10000)));
        assertEquals(bullets, myGame.getLevelManager().getCurrentLevel().getSprites().size());
    }

    @Test
    public void testTrackingBullets () {
        
           createTower();
           createTrackingFirer();
           myTower.addModule(myTrackingFirer);
        //myTower.getModuleDefinitions().clear();
//        myTower.addModule(myTrackingFirer);
        
        List<ISprite> towerList = new ArrayList<ISprite>();
        int bullets = 1;
        for (int i = 0; i < bullets; i++) {
            towerList.add(myTower.create());
        }

        towerList.stream().forEach(sprite -> sprite.update(new TimeDuration(10000)));
        assertEquals(bullets, myGame.getLevelManager().getCurrentLevel().getSprites().size());

    }

}
