package engine.effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.concrete.AttributeDefinition;


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
            return null;
        }
    }

    private Effect getEffectFromClass (Class<?> effectClass,
                                       Attribute length,
                                       AttributeDefinition def,
                                       double val) {
        try {
            Constructor<?> constructor =
                    effectClass.getConstructor(AttributeType.class, IAttribute.class, double.class);
            Object obj = constructor.newInstance(new AttributeType(def.getType()), length, val);
            return (Effect) obj;
        }
        catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
}
