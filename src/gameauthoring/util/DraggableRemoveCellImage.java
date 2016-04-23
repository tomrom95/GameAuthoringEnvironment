package gameauthoring.util;

import engine.profile.IProfilable;
import javafx.scene.Node;
import javafx.scene.control.Label;


public class DraggableRemoveCellImage<T extends IProfilable> extends DraggableRemoveCell<T> {

    private static final double PIC_SIZE = 150;
    private UIFactory myUIFactory = new UIFactory();

    public DraggableRemoveCellImage (Node target) {
        super(target);
    }

    @Override
    protected Node createSpriteCell (T profile) {
        Label label = new Label(profile.getProfile().getName().get());
        Node image = createImageProfile(profile.getProfile(), PIC_SIZE);
        Node node = myUIFactory.makeVBox(10, image, label);
        setActions(node);
        return node;
    }
}
