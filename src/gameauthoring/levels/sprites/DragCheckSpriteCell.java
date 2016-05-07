// This entire file is part of my masterpiece.
// Tommy Romano
package gameauthoring.levels.sprites;

import java.util.ResourceBundle;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.levels.SceneController;
import gameauthoring.util.BasicUIFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


/**
 * This class extends the DraggableSpriteCell to also show checking. This class
 * shows off the benefits of interfaces. First, the class continues with the
 * decorator pattern by creating its superclass's sprite cell and then adding
 * on a check box to the right of it. Second, it implements this Checkable
 * interface to handle what happens when the sprite cell is checked or not.
 * I set up this interface to be used in any part of the project that requires
 * a checkbox, like perhaps the forms. Because of the extensibility, any class
 * can implement it, and a class could perform actions on many of these with
 * only using the "isChecked" method, like, perhaps removing it from a list
 * if it's unchecked.  Additionally, this class uses a resource bundle to generate
 * the label, in the event that the language is changed. And, it uses the
 * UI factory to create the HBox. This makes sure this class has a single
 * responsibility- to add a checkbox, and add the actions. The controller handles
 * the actual implementation of the actions, and the UI factory handles the actual
 * creation of nodes to be used.
 * 
 * This class also employs some JavaFX listeners through lambda expressions. This
 * is the easiest way for functions to be called asynchronously. 
 * 
 * @author Tommy
 *
 */
public class DragCheckSpriteCell extends DraggableSpriteCell implements Checkable {

    private static final int BOX_SPACING = 5;

    private ResourceBundle myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());
    private CheckBox myCheckBox;

    public DragCheckSpriteCell (LevelRenderer target, SceneController controller) {
        super(target, controller);
    }

    @Override
    protected Node createSpriteCell (SpriteDefinition profile) {
        Node cell = super.createSpriteCell(profile);
        return createCheckCell(cell);
    }

    /**
     * Creates the actual check cell box using the UI factory
     * 
     * @param cell
     * @return
     */
    private Node createCheckCell (Node cell) {
        HBox box = new BasicUIFactory().makeHBox(BOX_SPACING, Pos.CENTER, cell, createCheckBox());
        return box;
    }

    /**
     * Helper to setup the checkbox by creating it, setting the default value
     * setting the listener, adding the tool tip, and returning it.
     * 
     * @return
     */
    private Node createCheckBox () {
        myCheckBox = new CheckBox();
        setDefault(myCheckBox);
        setListener(myCheckBox);
        addToolTip(myCheckBox);
        return myCheckBox;
    }

    /**
     * Sets a listener on the selected property of the checkBox to be
     * called whenever someone checks or unchecks the box
     * 
     * @param checkBox - checkbox to add the toolbox to
     */
    private void setListener (CheckBox checkBox) {
        checkBox.selectedProperty()
                .addListener( (observable, oldValue, newValue) -> checkBoxChange(oldValue,
                                                                                 newValue));
    }

    /**
     * Sets the default value for the check box upon instantiation
     * 
     * @param checkBox - checkbox to add the toolbox to
     */
    private void setDefault (CheckBox checkBox) {
        checkBox.setSelected(getController().isSpriteInLevel(getProfilable()));
    }

    /**
     * Adds a hover text box just to show what the checkbox actually does
     * 
     * @param checkBox - checkbox to add the toolbox to
     */
    private void addToolTip (CheckBox checkBox) {
        Tooltip tooltip = new Tooltip(myLabel.getString("PlaceableLevel"));
        checkBox.setTooltip(tooltip);
    }

    @Override
    public void doOnCheck () {
        getController().addSpriteToLevel(getProfilable());
    }

    @Override
    public void doOnUnCheck () {
        getController().removeSpriteFromLevel(getProfilable());
    }

    @Override
    public boolean isChecked () {
        return myCheckBox.isSelected();
    }

}
