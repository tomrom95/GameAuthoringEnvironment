package gameauthoring.creation.cellviews;

import engine.profile.IProfilable;
import gameauthoring.waves.ListGraphicFactory;
import javafx.scene.layout.HBox;


/**
 * Profile cell that can be deleted
 * 
 * @author RyanStPierre
 *
 * @param <E>
 */
public class DeleteableProfileCellView<E extends IProfilable> extends ProfileCellView<E> {

    private ListGraphicFactory myFactory = new ListGraphicFactory();

    @Override
    protected HBox createSpriteCell (E profile) {
        HBox container = super.getHBox(profile);
        container.getChildren().add(myFactory.createDelete(getListView().getItems(), profile));
        return container;
    }

}
