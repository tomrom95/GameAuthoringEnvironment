package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;

public class SubFormControllerFactory {

    public ISubFormControllerSprite createSpriteSubFormController(String type){
        
        if (type.equals("Profile")){
            System.out.println("profile");
            return new ProfileSubFormController();
            
        }
        /*else if (type.equals("Movement")){
            
        }
        */
        else if (type.equals("SmartAI")){
            System.out.println("smartAI");

            return new SmartAIMovementSubFormController();
        }else if (type.equals("UserMover")){
            System.out.println("userMover");

            return new UserMoverSubFormController();
        }else if (type.equals("")){
            
        }
        System.out.println("null");

        return null;
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
        if(type.equals("Attribute")){
            System.out.println("attribute");
            return new AttributeSubFormController();
        }
        System.out.println("null");

        return null;
        //return new AttributeSubFormController();
    }
}
