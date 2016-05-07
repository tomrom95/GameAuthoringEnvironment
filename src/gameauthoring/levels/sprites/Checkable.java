// This entire file is part of my masterpiece.
// Tommy Romano

package gameauthoring.levels.sprites;

/**
 * Interface to represent a checkable object. It takes advantage of the 
 * new default methods for interfaces, by creating a simple checkbox
 * change method. Of course, this is open to extension by just overwriting
 * the method, like, for instance, if you wanted to reverse the check 
 * and uncheck methods.
 * 
 * Additionally, this interface leaves it open to what object actually
 * wants to be checkable. I implement it with a JavaFX check box
 * but there is nothing stopping the user from implementing this interface
 * with, say, a button.
 * 
 * @author Tommy
 *
 */
public interface Checkable {
    
    /**
     * Default method to handle the check box change. Can be attached
     * to basically any listener.
     * @param oldValue - old value of the check box
     * @param newValue - new value of the check box
     */
    default void checkBoxChange (Boolean oldValue, Boolean newValue) {
        if (!oldValue && newValue) {
            doOnCheck ();
        }
        else if (oldValue && !newValue) {
            doOnUnCheck ();
        }
    }
    
    /**
     * The action to perform when the box is checked
     */
    void doOnCheck ();
    
    /**
     * The action to perform when the box is unchecked
     */
    void doOnUnCheck ();
    
    /**
     * Public API to see if the checkable object is checked
     * @return
     */
    boolean isChecked ();

}
