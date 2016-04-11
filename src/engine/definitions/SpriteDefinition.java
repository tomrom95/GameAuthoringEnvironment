package engine.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.IAttribute;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import graphics.IGraphic;
import util.Coordinate;


public class SpriteDefinition extends ProfileDefinition {

    private MovementDefinition myMovementDefinition;
    private List<ModuleDefiniton> myModuleDefinitions;
    private LocationDefinition myLocation;
    private List<AttributeDefinition> myAttributes;
    private IGraphic myGraphic;
    
    public SpriteDefinition () {
        // TODO Set a default. THis is just for view testing
        myMovementDefinition = new StaticMoverDefinition();
        myModuleDefinitions = new ArrayList<ModuleDefiniton>();
        myAttributes = new ArrayList<AttributeDefinition>();
        myLocation = new LocationDefinition();
    }

    public ISprite create () {
        ISprite sprite = new Sprite(new SpriteType(getName()));
        
        IMovementModule mover = myMovementDefinition.create(sprite);
        IGraphicModule graphicModule = new GraphicModule(myGraphic);
        Coordinate coord = myLocation.create();
        sprite.initialize(mover, graphicModule, createModules(), createAttributes(), coord);
        return sprite;
    }

    private List<IModule> createModules () {
        return myModuleDefinitions.stream()
                .map(modDef -> modDef.create())
                .collect(Collectors.toList());
    }

    private List<IAttribute> createAttributes () {
        return myAttributes.stream()
                .map(attDef -> attDef.create())
                .collect(Collectors.toList());
    }

    public void addModule (ModuleDefiniton definition) {
        myModuleDefinitions.add(definition);
    }

    public void addAttribute (AttributeDefinition attribute) {
        myAttributes.add(attribute);
    }

    public void removeAttribute (AttributeDefinition attribute) {
        myAttributes.remove(attribute);
    }

    public void setLocation (LocationDefinition location) {
        myLocation = location;
    }

    public void remove (ModuleDefiniton definition) {
        myModuleDefinitions.remove(definition);
    }

    public void setMovementDefinition (MovementDefinition definition) {
        myMovementDefinition = definition;
    }

    public void setType (String type) {
        this.setName(type);
    }
    
    public void setGraphic(IGraphic graphic){
        myGraphic = graphic;
    }
    
    public IGraphic getGraphic() {
        return myGraphic;
    }
}
