package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import engine.effects.DefaultAffectable;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


/**
 * AttributeManager holds a collection of of attributes and resources. The values within the
 * collection
 * are manipulated by effects.
 *
 */

public class AttributeManager extends DefaultAffectable implements IAttributeManager {

    private ObservableList<IAttribute> myAttributes;
    private List<IResource> myResources;

    public AttributeManager () {
        myAttributes = FXCollections.observableArrayList();
        myResources = new ArrayList<>();
    }

    @Override
    public void update (TimeDuration duration) {
        updateAttributeLoop(attribute -> attribute.update(duration));
    }

    private void updateAttributeLoop (Consumer<IAttribute> consume) {
        getAttributes().forEach(attribute -> consume.accept(attribute));
    }

    @Override
    public void applyEffect (IEffect effect) {
        updateAttributeLoop(attribute -> attribute.applyEffect(effect));
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        return myAttributes;
    }

    @Override
    public List<IResource> getResourceList () {
        return myResources;
    }

    @Override
    public void addResource (IResource resource) {
        resource.getAttributes().forEach(attribute -> myAttributes.add(attribute));
        myResources.add(resource);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        myAttributes.forEach(attribute -> attribute.registerKeyEvent(event));

    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        myAttributes.forEach(attribute -> attribute.registerMouseEvent(event));

    }

}
