package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SelectAttributeSFC;
import gameauthoring.creation.subforms.UpgradeSFC;
import gameauthoring.creation.subforms.costs.CostSFC;
import gameauthoring.creation.subforms.fire.FiringSFC;
import gameauthoring.creation.subforms.fire.FiringSFC;
import gameauthoring.creation.subforms.movement.MovementSFC;


public class SpriteSFCFactory extends SubFormControllerFactory<SpriteDefinition> {

    public SpriteSFCFactory (IGame game) {
        super(game);
    }

    @Override
    protected ISubFormController<SpriteDefinition> createSubFormController (String type, Object ... params) throws ReflectionException {

        if (type.equals("Movement")) {
            return new MovementSFC(getMyGame());
        }

        else if (type.equals("SelectAttribute")) {
            return new SelectAttributeSFC(getMyAuthorshipData()
                    .getMyCreatedAttributes());
        }
        else if (type.equals("Firing")) {
            return new FiringSFC(getMyGame());
        }
        else if (type.equals("UpgradeDefender")) {
            return new UpgradeSFC(getMyGame(),getMyGame().getAuthorshipData().getMyCreatedSprites("Defenders"));
        }
        else if (type.equals("UpgradeEnemy")) {
            return new UpgradeSFC(getMyGame(),getMyGame().getAuthorshipData().getMyCreatedSprites("Enemies"));
        }
        else if (type.equals("UpgradeMissile")) {
            return new UpgradeSFC(getMyGame(),getMyGame().getAuthorshipData().getMyCreatedMissiles());
        }
        else if (type.equals("Cost")) {
            return new CostSFC(getMyGame());
        }

        System.out.println("null");

        throw new ReflectionException("Can't create sprite subformcontroller of type " + type);
    }


}
