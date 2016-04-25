package gameauthoring.creation.entryviews;

import gameauthoring.util.Glyph;
import javafx.scene.control.Label;


/**
 * Interface for class to essentially serve as wrapper for two javaFX objects: label and some data
 * entry object. These should be bundled and organized by specific subFormView classes
 * 
 * @author JoeLilien
 *
 */
public interface IEntryView extends Glyph {

    FormData getData ();

    Label getLabel ();

}
