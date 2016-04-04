package gameauthoring.levels;

import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;


public class SpriteDragDrop {

    private Pane myTarget;
    private SpriteCellView mySource;

    public SpriteDragDrop (Pane target) {
        myTarget = target;
    }

    public void newDrag (SpriteCellView source, Node spriteNode) {
        setDragDetected(source, spriteNode);
        setDragOver();
        setDragDropped();
    }

    private void setDragOver () {
        myTarget.setOnDragOver(e -> {
            e.acceptTransferModes(TransferMode.COPY);
        });
    }

    private void setDragDropped () {
        myTarget.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                ImageView img = new ImageView(mySource.getImageProfile());
                img.setTranslateX(e.getX());
                img.setTranslateY(e.getY());
                createRightClickMenu(img);
                myTarget.getChildren().add(img);
            }
        });
    }

    private void createRightClickMenu (ImageView img) {
        img.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                spriteActionsMenu(myTarget, img).show(img,
                                                      event.getScreenX(),
                                                      event.getScreenY());
            }
        });
    }

    private ContextMenu spriteActionsMenu (Pane container, Node node) {
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            container.getChildren().remove(node);
        });
        menu.getItems().add(delete);
        return menu;
    }

    private void setDragDetected (SpriteCellView source, Node spriteNode) {
        mySource = source;
        Dragboard db = spriteNode.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(mySource.toString());
        db.setContent(content);
        db.setDragView(mySource.getImageProfile(), 0, 0);
    }

}
