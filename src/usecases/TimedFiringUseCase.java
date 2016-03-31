package usecases;

import org.easymock.EasyMock;
import engine.ISprite;
import engine.ISpriteCloner;
import util.Coordinate;
import util.TimeDuration;
import engine.IAdder;
import engine.IAttribute;
import engine.IEffect;
import engine.IGlobalEffect;
import engine.IModule;

/**
 * Demonstrates creating a weapon that fires on a specific time interval
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
        ISprite projectile = EasyMock.createMock(ISprite.class);
        IModule firingModule = new TimedFiringModuleUC(fireRate, projectile);
        sprite.getModulesProperty().add(firingModule);
        
        /**
         * Now it will
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

        @Override
        public void applyEffect (IEffect effect) {
            // do nothing
        }

        @Override
        public void update (TimeDuration duration) {

            myDuration.increase(duration);
            if (myDuration.getMillis() >= myFireRate.getValueProperty().get()) {
                fire();
                myDuration.setToZero();
            }

        }

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
        public void applyEffect (IGlobalEffect effect) {
            //Should not be influenced by global clicks or mouse input
            //Do nothing
            
        }

    }
}
