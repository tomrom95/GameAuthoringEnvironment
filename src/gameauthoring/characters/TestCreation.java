package gameauthoring.characters;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import engine.ISprite;
import engine.Sprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestCreation {

    private CreationController<ISprite> myCreationController;
    private ObservableList<ISprite> myCreationControllerItems;
    private ISprite myCreationControllerCurrentItem;
    
    @Before
    public void setup () {
        myCreationController = new CreationController<ISprite>();
       
        try{
            Field itemsField = CreationController.class.getDeclaredField("myItems");
            itemsField.setAccessible(true);
            myCreationControllerItems = (ObservableList<ISprite>) itemsField.get(myCreationController);
            
            
            Field currentItemField = CreationController.class.getDeclaredField("myCurrentItem");
            currentItemField.setAccessible(true);
            myCreationControllerCurrentItem = (ISprite) itemsField.get(myCreationController);

            
        }catch (Exception e) {
            assert(false);
        }
        
        myCreationControllerCurrentItem = new Sprite();
        myCreationControllerItems = FXCollections.observableArrayList();
        
        /*
        ISprite sprite1 = new Sprite();
        ISprite sprite2 = new Sprite();
        spriteList.add(sprite1);
        spriteList.add(sprite2);
        
        myCreationController.setItems(spriteList);
        */
    }
    
    @Test
    public void testDeletion () {
        myCreationControllerItems.add(myCreationControllerCurrentItem);

        try {
            Method deleteMethod = CreationController.class.getDeclaredMethod("deleteItem");
            deleteMethod.setAccessible(true);
        
            assertEquals(myCreationControllerItems.contains(myCreationControllerCurrentItem), true);
            deleteMethod.invoke(myCreationController);
            assertEquals(myCreationControllerItems.contains(myCreationControllerCurrentItem), false);
            
        } catch (Exception e) {
            assert(false);
        }
        
        
    }

}
