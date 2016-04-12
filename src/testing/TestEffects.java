package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import engine.effects.*;
import util.TimeDuration;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;

// import engine.eff


public class TestEffects {

    private static final String TEST_ATTY_NAME = "TestAttribute";
    private static final String DURATION_ATTY_NAME = "EffectDuration";

    private static final double DEFAULT_EFFECT_DURATION = 10d;
    private static final double DURATION_TICK = 20d;

    private IEffect myEffect;

    private IAttribute myAttribute;
    private AttributeType myAttributeType;

    private IAttribute myEffectLength;
    private AttributeType myEffectLengthAttributeType;

    private TimeDuration myDuration;

    @Before
    public void setUp () throws Exception {
        // Creating some standard attribute types, instances and starting values
        myAttributeType = new AttributeType(TEST_ATTY_NAME);
        myAttribute = new Attribute(myAttributeType);

        myEffectLengthAttributeType = new AttributeType(DURATION_ATTY_NAME);
        myEffectLength = new Attribute(myEffectLengthAttributeType);
        myEffectLength.setValue(DEFAULT_EFFECT_DURATION);

        myDuration = new TimeDuration(DURATION_TICK);

    }

    @Test
    public void increaseEffectTestValueSimple () {
        myEffect = new IncreaseEffect(myAttributeType, myEffectLength, 100d);
        myAttribute.applyEffect(myEffect);
        myAttribute.update(myDuration);
        assertEquals(100d, myAttribute.getValueProperty().get(), 0.1d);
    }

    @Test
    public void decreaseEffectTestValueSimple () {
        myEffect = new DecreaseEffect(myAttributeType, myEffectLength, 100d);
        myAttribute.applyEffect(myEffect);
        myAttribute.update(myDuration);
        assertEquals(-100d, myAttribute.getValueProperty().get(), 0.1d);
    }

    @Test
    public void proportionEffectTestValueSimple () {
        myAttribute.setValue(100d);
        myEffect = new ProportionEffect(myAttributeType, myEffectLength, 0.8d);
        myAttribute.applyEffect(myEffect);
        myAttribute.update(myDuration);
        assertEquals(80d, myAttribute.getValueProperty().get(), 0.1d);
    }

    @Test
    public void proportionEffectZeroTest () {
        myAttribute.setValue(100d);
        myEffect = new ProportionEffect(myAttributeType, myEffectLength, 0d);
        myAttribute.applyEffect(myEffect);
        myAttribute.update(myDuration);
        assertEquals(0d, myAttribute.getValueProperty().get(), 0.1d);
    }

    @Test
    public void proportionEffectNegValTest () {
        myAttribute.setValue(100d);
        myEffect = new ProportionEffect(myAttributeType, myEffectLength, -1d);
        myAttribute.applyEffect(myEffect);
        myAttribute.update(myDuration);
        assertEquals(-100d, myAttribute.getValueProperty().get(), 0.1d);
    }

}
