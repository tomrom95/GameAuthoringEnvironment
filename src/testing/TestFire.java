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
import engine.sprite.ISprite;
import util.TimeDuration;


public class TestFire {

    private SpriteDefinition myProjectile;
    private ConstantMoverDefinition myMover;
    private SpriteDefinition myTower;
    private DirectionalFirerDefinition myFirer;
    private Game myGame;
    private SpriteDefinition myEnemy;

    
    
    @Before
    public void setUp () {
        myGame = new Game();

        createMover();
        createProjectile();
        createTower();
        createFirer();

        myTower.addModule(myFirer);

        createEnemy();
    }

    private void createProjectile () {
        myProjectile = new SpriteDefinition();
        myProjectile.setMovementDefinition(myMover);
    }

    private void createEnemy () {
        myEnemy = new SpriteDefinition();
        LocationDefinition enemyLocation = new LocationDefinition();
        enemyLocation.setX(100);
        enemyLocation.setY(100);
        myEnemy.setLocation(enemyLocation);
    }

    private void createFirer () {
        myFirer = new DirectionalFirerDefinition();
        myFirer.setProjectileDefinition(myProjectile);
        myFirer.setWaitTime(1000);
        myFirer.setGame(myGame);
        myFirer.setAngle(120);
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
    public void testBulletCreation () {
        List<ISprite> towerList = new ArrayList<ISprite>();
        int bullets = 100;
        for (int i = 0; i < bullets; i++) {
            towerList.add(myTower.create());
        }
        
        towerList.stream().forEach(sprite -> sprite.update(new TimeDuration(10000)));
        assertEquals(bullets, myGame.getLevelManager().getCurrentLevel().getSprites().size());
    }

    @Test
    public void testUserFirer () {

    }

}
