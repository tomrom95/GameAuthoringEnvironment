package testing;

import java.util.function.DoublePredicate;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import util.predicates.RealDoublePredicate;

public class TestFuncInterDeserialize {

    public static void main (String[] args) {
        RealDoublePredicate dp = new RealDoublePredicate();
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        xstream.setMode(XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES);
        String xml = xstream.toXML(dp);
        System.out.println(xml);
        DoublePredicate dp2 = (RealDoublePredicate) xstream.fromXML(xml);
        
    }
}
