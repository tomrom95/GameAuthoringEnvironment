package gameauthoring.waves;

import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

/**
 * Used to create drag and drop reorder list view 
 * @author RyanStPierre
 *
 */

public class WaveDragCell extends ListCell<WaveBlockDefinition> {

    ListGraphicFactory myFactory = new ListGraphicFactory();
    private XStream myXStream;
    public WaveDragCell () {

        initXStream();
        this.setOnDragDetected(event -> dragStart(event));
        this.setOnDragDropped(event -> drop(event));
        this.setOnDragOver(event -> over(event));

    }

    /**
     * Needed to recover drag information 
     */
    private void initXStream () {
        myXStream = new XStream(new DomDriver());
        FXConverters.configure(myXStream);
        myXStream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);
        
    }

    private void over (DragEvent event) {
        if (event.getGestureSource() != this &&
            event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    private void drop (DragEvent event) {

        if (getItem() == null) {
            return;
        }

        WaveBlockDefinition retrieved = retrieve(event.getDragboard().getString());
        int index = getListView().getItems().indexOf(this.getItem());
        getListView().getItems().add(index, retrieved);

    }

    private WaveBlockDefinition retrieve (String data) {
        return (WaveBlockDefinition) myXStream.fromXML(data);
    }

    private void dragStart (MouseEvent event) {
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent cc = new ClipboardContent();
        db.setDragView(myFactory.getImage(this), 0, 0);
        cc.putString(serialize(this.getItem()));
        db.setContent(cc);
        event.consume();
        getListView().getItems().remove(this.getItem());
    }

    private String serialize (WaveBlockDefinition item) {
        return myXStream.toXML(item);
    }

    @Override
    protected void updateItem (WaveBlockDefinition item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        }
        else {
            setGraphic(myFactory.createGraphic(item));
        }
    }

}
