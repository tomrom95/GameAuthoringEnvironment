package gameauthoring.creation.forms;

import java.util.ArrayList;
import java.util.List;
import engine.AuthorshipData;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;


public class CreationControllerFactory {

    public CreationControllerFactory(){
     
    }
    
    public CreationControllerSprite createSpriteCreationController(String title, List<String> sfcs,  AuthorshipData authorshipData ){
        return new CreationControllerSprite(title, sfcs, authorshipData);
    }
    public CreationControllerAttribute createAttributeCreationController(String title, List<String> sfcs,  AuthorshipData authorshipData ){
        return new CreationControllerAttribute(title, sfcs, authorshipData);
    }
}
