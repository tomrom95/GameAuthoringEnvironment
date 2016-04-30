package engine.effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.util.ErrorMessage;


public class EffectFactory {

    private static final String PACKAGE = "engine.effects.";
    private static final String SUFFIX = "Effect";

    public Effect getEffect (String effectType,
                             Attribute length,
                             AttributeDefinition def,
                             double val) {
        try {
            Class<?> effectClass = Class.forName(PACKAGE + effectType + SUFFIX);
            return getEffectFromClass(effectClass, length, def, val);

        }
        catch (ClassNotFoundException e) {
            ErrorMessage err = new ErrorMessage(e.getMessage());
            err.showError();
            return null;
        }
    }

    private Effect getEffectFromClass (Class<?> effectClass,
                                       Attribute length,
                                       AttributeDefinition def,
                                       double val) {
        try {
            Constructor<?> constructor =
                    effectClass.getConstructor(AttributeDefinition.class, IAttribute.class, double.class);
            Object obj = constructor.newInstance(def, length, val);
            return (Effect) obj;
        }
        catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            ErrorMessage err = new ErrorMessage(e.getMessage());
            err.showError();
            e.printStackTrace();
            return null;
        }
    }
}
