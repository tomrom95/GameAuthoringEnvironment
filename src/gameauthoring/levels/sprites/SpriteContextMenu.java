package gameauthoring.levels.sprites;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.ResourceBundle;
import engine.rendering.AuthoringRenderer;
import engine.sprite.ISprite;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import util.Coordinate;

public class SpriteContextMenu {
    
    private AuthoringRenderer levelView;
    private ISprite mySprite;
    private SpriteController myController;
    private ResourceBundle myResources;
    
    public SpriteContextMenu (AuthoringRenderer renderer, SpriteController controller,
                              ISprite sprite) {
        levelView = renderer;
        myController = controller;
        mySprite = sprite;
        myResources = ResourceBundle.getBundle("resource/SpriteContextMenu");
    }
    
    public ContextMenu createActionMenu (){
        ContextMenu menu = new ContextMenu();
        Enumeration<String> keys = myResources.getKeys();
        while(keys.hasMoreElements()){
            menu.getItems().add(createMenuItem(keys.nextElement()));
        }
        
        return menu;
    }
    
    private MenuItem createMenuItem (String name) {
        MenuItem newItem = new MenuItem(myResources.getString(name));

        try {
            Method method = this.getClass().getMethod(name);
            newItem.setOnAction(event -> callMethod(method));
        }
        catch (NoSuchMethodException | SecurityException e) {
        }
        return newItem;

    }
    
    private void callMethod (Method method) {
        try {
            method.invoke(this);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return;
        }
    }

    public void delete () {
        myController.deleteSprite(mySprite);
        levelView.render();
    }
    
    public void createPath () {
        Coordinate point = mySprite.getLocation();
        myController.createNewPath(point, levelView.getPane());
    }
    

}
