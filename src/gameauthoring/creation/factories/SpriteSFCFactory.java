package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SelectAttributeSFC;
import gameauthoring.creation.subforms.UpgradeSFC;
import gameauthoring.creation.subforms.costs.CostSFC;
import gameauthoring.creation.subforms.fire.FiringSFC;
import gameauthoring.creation.subforms.fire.FiringSFCmult;
import gameauthoring.creation.subforms.movement.MovementSFC;


public class SpriteSFCFactory extends SubFormControllerFactory<SpriteDefinition> {

    public SpriteSFCFactory (IGame game) {
        super(game);
    }

    @Override
    protected ISubFormController<SpriteDefinition> createSubFormController (String type) {

        if (type.equals("Movement")) {
            return new MovementSFC(getMyGame());
        }

        else if (type.equals("SelectAttribute")) {
            return new SelectAttributeSFC(getMyAuthorshipData()
                    .getMyCreatedAttributes());
        }
        else if (type.equals("FireMult")) {
            return new FiringSFCmult(getMyGame());
        }
        else if (type.equals("Upgrade")) {
            return new UpgradeSFC(getMyGame());
        } else if (type.equals("Cost")) {
            return new CostSFC(getMyGame());
        }

        System.out.println("null");

        return null;
    }

}
