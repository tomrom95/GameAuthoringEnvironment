package engine.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import engine.IAttribute;
import engine.definitions.upgrades.NullUpgradeDefinition;
import engine.definitions.upgrades.UpgradeDefinition;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.modules.IModule;
import engine.modules.IMovementModule;
import engine.modules.UpgradeModule;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import util.Coordinate;


/**
 * This class represents the definition of a sprite to be created at a later point
 *
 */
public class SpriteDefinition implements IProfilable {

    private MovementDefinition myMovementDefinition;
    private List<ModuleDefinition> myModuleDefinitions;
    private UpgradeDefinition myUpgrade;
    private Coordinate myLocation;
    private List<AttributeDefinition> myAttributes;
    private IProfile myProfile;
    private SpriteType mySpriteType;

    public SpriteDefinition () {
        // TODO Set a default from resource file. THis is just for view testing
        myMovementDefinition = new StaticMovementDefintion();
        myUpgrade = new NullUpgradeDefinition();
        myModuleDefinitions = new ArrayList<ModuleDefinition>();
        myAttributes = new ArrayList<AttributeDefinition>();
        myLocation = new Coordinate(0, 0);
        myProfile = new Profile();
    }

    public ISprite create () {

        ISprite sprite = new Sprite(new SpriteType(myProfile.getName().get()));
        IMovementModule mover = myMovementDefinition.create(sprite);
        IGraphicModule graphicModule = createGraphicModule();
        sprite.initialize(mover, graphicModule, createUpgrade(sprite), createModules(sprite),
                          createAttributes(), createCoordinate());
        return sprite;
    }
    
    protected UpgradeModule createUpgrade (ISprite parent) {
        return myUpgrade.create(parent);
    }

    protected IGraphicModule createGraphicModule () {
        return new GraphicModule(myProfile.getImage());
    }

    protected Coordinate createCoordinate () {
        return myLocation;
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

    public void setLocation (Coordinate location) {
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

    public void setUpgrade (UpgradeDefinition upgrade) {
        myUpgrade = upgrade;
    }

    public List<ModuleDefinition> getModuleDefinitions () {
        return myModuleDefinitions;
    }

    public SpriteType getSpriteType () {
        // TODO: check if this should be one reference or new one every time

        return new SpriteType(getProfile().getName().get());
    }

}
