package gameauthoring.creation.subforms.events;

import gameauthoring.creation.subforms.ISubFormView;


/**
 * View that allows users to create a Game Event to be added to an Event Package
 *
 * @author Joe Lilien
 *
 */
public interface IEventSFV extends ISubFormView {

    String getEventSelection ();

    void setEventSelection (String eventType);

}
