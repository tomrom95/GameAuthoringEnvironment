package usecases;

import org.easymock.EasyMock;
import engine.ISprite;
import engine.ISpriteCloner;
import util.Coordinate;
import util.TimeDuration;
import engine.IAdder;
import engine.IAttribute;
import engine.IEffect;
import engine.IInteractionEvent;
import engine.IModule;


/**
 * Demonstrates creating a weapon that fires on a specific time interval. To do such a class
 * implementing the IModule interface is created to give the desired functionality. Here the
 * firing module can be thought of as a Spawner that spawns the desired projectile on the time
 * interval specified.
 * 
 * @author RyanStPierre
 *
 */

public class TimedFiringUseCase {

    ISprite sprite;

    public TimedFiringUseCase () {
        sprite = EasyMock.createMock(ISprite.class);
    }

    private void demonstrateUseCase () {

        /**
         * At this point upon updating ISprite will not fire
         */

        double fireRate = 10;

        /**
         * This projectile can be any type of ISprite. The collisions, damage that a weapon does is
         * tied directly to what its projectile does.
         */
        ISprite projectile = EasyMock.createMock(ISprite.class);
        IModule firingModule = new TimedFiringModuleUC(fireRate, projectile);
        sprite.getModulesProperty().add(firingModule);

        /**
         * Now it will. The firing modules is created with the specified parameters and then applied
         * to the Sprite to give the desired functionality. The Sprite updates is modules upon
         * updating itself.
         */

    }

    public class TimedFiringModuleUC implements IModule {

        private TimeDuration myDuration;
        private IAttribute myFireRate;
        private IAdder myAdder;
        private ISprite myProjectile;

        public TimedFiringModuleUC (double fireRate, ISprite projectile) {
            sprite = EasyMock.createMock(ISprite.class);
            myAdder = EasyMock.createMock(IAdder.class);
        }

        /**
         * The only attribute this module contains is the fire rate. The fire rate
         * passes itself to the effect. The effect will only do something if it stores he
         * AttributeType myFireRate.
         */
        @Override
        public void applyEffect (IEffect effect) {
            effect.applyToAttribute(myFireRate);
        }

        /**
         * If the desired time has passed the weapon fires
         */
        @Override
        public void update (TimeDuration duration) {

            myDuration.increase(duration);
            if (myDuration.getMillis() >= myFireRate.getValueProperty().get()) {
                fire();
                myDuration.setToZero();
            }

        }

        /**
         * This methods fires the weapon. To do this the ISpriteCloner is used to create a new
         * instance
         * of the projectile that the weapon is supposed to fire. The projectile is simply a Sprite.
         * In addition the firing needs a way to add to the given level. To do this it is given
         * access to the LevelManager in the form of the IAdder interface, which just exposes the
         * add
         * method.
         */
        private void fire () {

            ISpriteCloner cloner = EasyMock.createMock(ISpriteCloner.class);
            myAdder.add(cloner.clone(myProjectile), new Coordinate(getX(), getY()));

        }

        /**
         * Need to implement to have reference to the xy-location of the Sprite to which it is
         * contained
         * Need to consult team
         */
        private double getX () {
            return 0;
        }

        private double getY () {
            return 0;
        }

        @Override
        public void registerEvent (IInteractionEvent effect) {
            // Should not be influenced by global clicks or mouse input
            // Do nothing

        }

    }
}
