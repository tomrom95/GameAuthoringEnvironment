package engine.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.IAttribute;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import util.Coordinate;


public class SpriteDefinition implements IProfilable {

    private MovementDefinition myMovementDefinition;
    private List<ModuleDefinition> myModuleDefinitions;
    private LocationDefinition myLocation;
    private List<AttributeDefinition> myAttributes;
    private IProfile myProfile;
    private SpriteType mySpriteType;

    public SpriteDefinition () {
        // TODO Set a default. THis is just for view testing
        myMovementDefinition = new StaticMoverDefinition();
        myModuleDefinitions = new ArrayList<ModuleDefinition>();
        myAttributes = new ArrayList<AttributeDefinition>();
        myLocation = new LocationDefinition();
        myProfile = new Profile();
    }

    public ISprite create () {
        ISprite sprite = new Sprite(new SpriteType(myProfile.getName()));

        IMovementModule mover = myMovementDefinition.create(sprite);
        IGraphicModule graphicModule = createGraphicModule();
        sprite.initialize(mover, graphicModule, createModules(sprite), createAttributes(),
                          createCoordinate());
        return sprite;
    }

    protected IGraphicModule createGraphicModule () {
        return new GraphicModule(myProfile.getImage());
    }

    protected Coordinate createCoordinate () {
        return myLocation.create();
    }

    protected List<IModule> createModules (ISprite sprite) {
        return myModuleDefinitions.stream()
                .map(modDef -> modDef.create(sprite))
                .collect(Collectors.toList());
    }

    protected List<IAttribute> createAttributes () {
        return myAttributes.stream()
                .map(attDef -> attDef.create())
                .collect(Collectors.toList());
    }

    public void addModule (ModuleDefinition definition) {
        myModuleDefinitions.add(definition);
    }

    public void addAttribute (AttributeDefinition attribute) {
        myAttributes.add(attribute);
    }

    public List<AttributeDefinition> getAttributes () {
        return myAttributes;
    }

    public void setAttributes (List<AttributeDefinition> attributes) {
        myAttributes = new ArrayList<AttributeDefinition>(attributes);
    }

    public void removeAttribute (AttributeDefinition attribute) {
        myAttributes.remove(attribute);
    }

    public void setLocation (LocationDefinition location) {
        myLocation = location;
    }

    public void remove (ModuleDefinition definition) {
        myModuleDefinitions.remove(definition);
    }

    public MovementDefinition getMovementDefinition () {
        return myMovementDefinition;
    }

    public void setMovementDefinition (MovementDefinition definition) {
        myMovementDefinition = definition;
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }
    
    public SpriteType getSpriteType() {
        //TODO: check if this should be one reference or new one every time
        
        return new SpriteType(getProfile().getName());
    }

}
