package gameauthoring.listdisplay;

import gameauthoring.creation.factories.ReflectionException;


/**
 * Interface to define factories that are able to take in a String and return the proper
 * SubConditionView
 *
 * @author RyanStPierre
 *
 */
public interface ISubViewFactory {

    /**
     * Returns the proper SubConditionView subclass (type) based on the String input
     *
     * @param input
     * @return
     * @throws ReflectionException
     */
    SubConditionView interpret (String input) throws ReflectionException;
}
