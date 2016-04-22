package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.EventPackageDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;

public class EventsSubFormController extends DynamicSubFormController<EventPackageDefinition>{

    public EventsSubFormController (IGame game) {
       super(game, new EventSFCFactory(game),
             new ArrayList<String>(Arrays.asList("Event", "Effect")));
       List<String> options = new ArrayList<>(Arrays.asList("Event", "Attribute Effect"));
       setMyView(new EventsSubFormView(getMySubFormViews(),
                                       e -> changeSelection(e),
                                       options));
    }
    
//    private EventsSubFormView myView;
//    private IDefinitionCollection<EventPackageDefinition> myCreatedEvents;
//    
//    public EventsSubFormController (IDefinitionCollection<AttributeDefinition> myCreatedAttributes,
//                                    IDefinitionCollection<EventPackageDefinition> events) {
//        this.myView = new EventsSubFormView(myCreatedAttributes);
//        myCreatedEvents = events;
//    }
//
//    @Override
//    public void updateItem (EventPackageDefinition item) {
//        if(myView.getEffectData() != null){
//            makeNewEffect(myView.getEffectData(), item);
//        } else {
//            makeNewEvent(myView.getEventData(), item);
//        }
//    }
//
//    private void makeNewEffect (IFormDataManager eventData, EventPackageDefinition item) {
//        AttributeDefinition attr = myView.getEffectView().getAttribute();
//        Attribute a = new Attribute(new AttributeType("length"));
//        a.setValue(Double.valueOf(eventData.getValueProperty("Length").get()));
//        double val = Double.valueOf(eventData.getValueProperty("Amount").get());
//        Effect effect = new DecreaseEffect(new AttributeType(attr.getType()), a, val);
//        item.setMyEffectsList(new ArrayList<IEffect>(Arrays.asList(effect)));
//    }
//
//    private void makeNewEvent (IFormDataManager effectData, EventPackageDefinition item) {
//        GameEvent event = new GameEvent(EventType.DEATH);
//        item.setMyEventsList(new ArrayList<GameEvent>(Arrays.asList(event)));
//    }
//
//    @Override
//    public ISubFormView getSubFormView () {
//        return myView;
//    }
//
//    @Override
//    public void initializeFields () {
//        // TODO Auto-generated method stub
//        
//    }

}
