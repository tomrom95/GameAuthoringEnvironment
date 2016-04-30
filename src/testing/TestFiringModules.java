package testing;

import java.util.ArrayList;
import java.util.List;
import engine.Game;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
import engine.definitions.moduledef.TrackingFirerDefinition;
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


// TODO : remove class
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
    protected void setUp () {
        game =
                new Game(new GameGridConfigNonScaling((int) GamePlayer.PREFWIDTH,
                                                      (int) GamePlayer.PREFHEIGHT));

        projectile = new SpriteDefinition();
        mover = new ConstantMoverDefinition();
        mover.setSpeed(100);
        mover.setSpeed(10);
        mover.setOrientaiton(0);
        projectile.setMovementDefinition(mover);

        shooter = new SpriteDefinition();
        shooter.setLocation(new Coordinate(0, 0));

        fire = new DirectionalFirerDefinition();
        fire.setProjectileDefinition(projectile);
        fire.setWaitTime(1000);
        fire.setGame(game);
        fire.setAngle(-60);

        enemy = new SpriteDefinition();
        enemy.setLocation(new Coordinate(100, 100));
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

    public void testFire () {
        double speed = projectile.getMovementDefinition().getSpeed();

        ISprite shooterGuy = shooter.create();

        game.add(shooterGuy);
        game.add(enemy.create());
        game.update(new TimeDuration(100000));
        game.update(new TimeDuration(200000));

        assertEquals(3, game.getLevelManager().getCurrentLevel().getSprites().size());

    }

}
