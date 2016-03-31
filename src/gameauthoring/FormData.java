package gameauthoring;

/**
 * Object representation of a form entry to make data collection
 * very simple. Every part of the form has a label for easy find
 * access and a string representation of the data. This would work
 * for text boxes, combo boxes, and other forms of entry.
 * @author Tommy
 *
 */
public interface FormData {

    public String getLabel();
    
    public String getData();
}
