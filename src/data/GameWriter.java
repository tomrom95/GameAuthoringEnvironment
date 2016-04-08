package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.dooapp.xstreamfx.FXConverters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.Attribute;
import engine.AttributeType;
import engine.Game;
import engine.IAttribute;
import engine.modules.PathFollowMover;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import util.Coordinate;

public class GameWriter implements IGameWriter{

    @Override
    public File serialize (Game game) {
     
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        return null;
    }
    
    public static void main(String [ ] args) {
        XStream xstream = new XStream(new DomDriver());
        FXConverters.configure(xstream);
        List<Coordinate> list = new ArrayList<>();
        ISprite sprite = new Sprite();
        PathFollowMover mover = new PathFollowMover(2, list, sprite);
        String xml = xstream.toXML(mover);
        System.out.println(xml);
        PathFollowMover moverAfter = (PathFollowMover) xstream.fromXML(xml);
        System.out.print(moverAfter.getMySpeed());
        
        IAttribute attribute = new Attribute(3, new AttributeType("Ryan"));
        System.out.println(attribute.getValueProperty().get());
        String a = xstream.toXML(attribute);
        IAttribute attribute2 = (IAttribute) xstream.fromXML(a);
        System.out.println(attribute2.getValueProperty().get());
    }

}
