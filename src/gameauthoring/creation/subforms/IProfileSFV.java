package gameauthoring.creation.subforms;

/**
 * Defines necessary methods for a ProfileSubFormView, allows for full customization of JavaFX
 * objects and arrangement implemented, while still avoiding hidden dependency
 * 
 * @author Joe Lilien
 *
 */
public interface IProfileSFV  extends ISubFormView{


    double getMyImageWidth ();


    double getMyImageHeight ();


    String getName ();


    String getDescription ();


    String getImage ();
    
    void populateWithData (String name, String description, String imageURL, double width, double height);
    
}
