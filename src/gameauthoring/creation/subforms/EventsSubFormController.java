package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import engine.Attribute;
import engine.AttributeType;
import engine.definitions.AttributeDefinition;
import engine.definitions.EventPackageDefinition;
import engine.effects.DecreaseEffect;
import engine.effects.Effect;
import engine.effects.IEffect;
import engine.events.EventType;
import engine.events.GameEvent;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.shareddata.IDefinitionCollection;

public class EventsSubFormController implements ISubFormController<EventPackageDefinition>{
    
    private EventsSubFormView myView;
    private IDefinitionCollection<EventPackageDefinition> myCreatedEvents;
    
    public EventsSubFormController (IDefinitionCollection<AttributeDefinition> myCreatedAttributes,
                                    IDefinitionCollection<EventPackageDefinition> events) {
        this.myView = new EventsSubFormView(myCreatedAttributes);
        myCreatedEvents = events;
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        if(myView.getEffectData() != null){
            makeNewEffect(myView.getEffectData(), item);
        } else {
            makeNewEvent(myView.getEventData(), item);
        }
    }

    private void makeNewEffect (IFormDataManager eventData, EventPackageDefinition item) {
        AttributeDefinition attr = myView.getEffectView().getAttribute();
        Attribute a = new Attribute(new AttributeType("length"));
        a.setValue(Double.valueOf(eventData.getValueProperty("Length").get()));
        double val = Double.valueOf(eventData.getValueProperty("Amount").get());
        Effect effect = new DecreaseEffect(new AttributeType(attr.getType()), a, val);
        item.setMyEffectsList(new ArrayList<IEffect>(Arrays.asList(effect)));
    }

    private void makeNewEvent (IFormDataManager effectData, EventPackageDefinition item) {
        GameEvent event = new GameEvent(EventType.DEATH);
        item.setMyEventsList(new ArrayList<GameEvent>(Arrays.asList(event)));
    }

    @Override
    public void populateViewsWithData (EventPackageDefinition item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
