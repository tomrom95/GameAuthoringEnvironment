package engine.effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.definitions.AttributeDefinition;


public class EffectFactory {

    private static final String SUFFIX = "Effect";

    public Effect getEffect (String effectType,
                             Attribute length,
                             AttributeDefinition def,
                             double val) {
        try {
            Class<?> effectClass = Class.forName(effectType + SUFFIX);
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
            Object obj = constructor.newInstance(def.getType(), length, val);
            if (obj instanceof Effect) {
                return (Effect) obj;
            }
            return null;
        }
        catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
}
