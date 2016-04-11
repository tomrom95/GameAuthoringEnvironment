package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;

public class SubFormControllerFactory {

    public ISubFormControllerSprite createSpriteSubFormController(String type){
        /*
        if (type.equals("Profile")){
            return new ProfileSubFormController();
            
        }else if (type.equals("Movement")){
            
        }
        */
        return new ProfileSubFormController();
    }
    
    public List<ISubFormControllerSprite> createSpriteSubFormControllers(String[] subFormStrings){
        List<ISubFormControllerSprite> list = new ArrayList<ISubFormControllerSprite>();
        for(int i = 0; i <subFormStrings.length; i++) {
            String subFormString = subFormStrings[i];
            list.add(createSpriteSubFormController(subFormString));
        }
        return list;
    }
    
    public List<ISubFormControllerAttribute> createAttributeSubFormControllers(String[] subFormStrings){
        List<ISubFormControllerAttribute> list = new ArrayList<ISubFormControllerAttribute>();
        for(int i = 0; i <subFormStrings.length; i++) {
            String subFormString = subFormStrings[i];
            list.add(createAttributeSubFormController(subFormString));
        }
        return list;
    }
    public ISubFormControllerAttribute createAttributeSubFormController(String type){
        return null;
        //return new AttributeSubFormController();
    }
}
