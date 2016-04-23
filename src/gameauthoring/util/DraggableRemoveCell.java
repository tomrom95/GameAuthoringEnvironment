package gameauthoring.util;

import engine.profile.IProfilable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;


public class DraggableRemoveCell<T extends IProfilable> extends DraggableCell<T> {

    private static final double PIC_SIZE = 150;
    private UIFactory myUIFactory = new UIFactory();

    public DraggableRemoveCell (Node target) {
        setTarget(target);
    }

    @Override
    protected Node createSpriteCell (T profile) {
        Label label = new Label(profile.getProfile().getName().get());
        Node image = createImageProfile(profile.getProfile(), PIC_SIZE);
        Node node =  myUIFactory.makeVBox(10, image , label);
        setActions(node);
        return node;
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.ANY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        getListView().getItems().remove(getIndex());
    }

    @Override
    public boolean checkPlaceable (DragEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

}
