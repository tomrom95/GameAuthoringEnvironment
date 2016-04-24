package gameauthoring.creation.subforms;

/**
 * Defines necessary methods for a ProfileSubFormView, allows for full customization of JavaFX
 * objects and arrangement implemented, while still avoiding hidden dependency
 * 
 * @author Joe Lilien
 *
 */
public interface IProfileSFV {

    String getMyNameKey ();


    String getMyDescriptionKey ();


    String getMyImageKey ();


    double getMyImageWidth ();


    double getMyImageHeight ();
    
}
