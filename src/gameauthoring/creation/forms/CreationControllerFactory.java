package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;

/**
 * This is a factory class for the creation of Creation Controllers
 * 
 * @author Jeremy Schreck
 *
 */
public class CreationControllerFactory {

    public CreationControllerFactory(){
     
    }
    
    public CreationControllerSprite createSpriteCreationController(String title, List<String> sfcs,  AuthorshipData authorshipData ){
        return new CreationControllerSprite(title, sfcs, authorshipData);
    }
    public CreationControllerAttribute createAttributeCreationController(String title, List<String> sfcs,  AuthorshipData authorshipData ){
        return new CreationControllerAttribute(title, sfcs, authorshipData);
    }
    
    public CreationControllerGroup createGroupCC(String title, List<String> sfcs, AuthorshipData authorshipData){
        return new CreationControllerGroup(title,sfcs,authorshipData);
    }
}
