package gameauthoring.creation.cellviews;

import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import gameauthoring.waves.ListGraphicFactory;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


/**
 * Used to create drag and drop reorder list view
 * For intra-list organization
 *
 * @author RyanStPierre
 *
 */

public class WaveDragCell extends ListCell<WaveBlockDefinition> {

    private ListGraphicFactory myFactory = new ListGraphicFactory();
    private XStream myXStream;
    private final String myComma = ",";

    public WaveDragCell () {

        initXStream();
        setOnDragDetected(event -> dragStart(event));
        setOnDragDropped(event -> drop(event));
        setOnDragOver(event -> over(event));

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

        int toRemove = getFirst(event.getDragboard().getString());
        String toRetrieve = getLast(event.getDragboard().getString());
        WaveBlockDefinition retrieved = retrieve(toRetrieve);
        int index = getListView().getItems().indexOf(getItem());
        getListView().getItems().remove(toRemove);
        getListView().getItems().add(index, retrieved);

    }

    /**
     * Returns the index information of the String
     *
     * @param string
     * @return
     */
    private int getFirst (String data) {
        return Integer.parseInt(data.substring(0, dividerIndex(data)));
    }

    /**
     * Returns the xml part of the copy
     *
     * @param string
     * @return
     */
    private String getLast (String data) {
        return data.substring(dividerIndex(data) + 1);
    }

    private int dividerIndex (String data) {
        return data.indexOf(myComma);
    }

    private WaveBlockDefinition retrieve (String data) {
        return (WaveBlockDefinition) myXStream.fromXML(data);
    }

    private void dragStart (MouseEvent event) {
        Dragboard db = startDragAndDrop(TransferMode.MOVE);
        ClipboardContent cc = new ClipboardContent();
        db.setDragView(myFactory.getTransferImage(this), 0, 0);
        cc.putString(serialize(getItem(), getListView().getItems().indexOf(getItem())));
        db.setContent(cc);
        event.consume();
    }

    private String serialize (WaveBlockDefinition item, int index) {
        String xml = myXStream.toXML(item);
        return index + myComma + xml;
    }

    @Override
    protected void updateItem (WaveBlockDefinition item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        }
        else {
            setGraphic(myFactory.createGraphic(getListView().getItems(), item));
        }
    }

}
