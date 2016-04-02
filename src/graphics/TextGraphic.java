package graphics;

import gameplayer.IGraphicFactory;
import javafx.scene.Node;


public class TextGraphic implements IGraphic{

	//TODO finish
	
    private String myText;
    private double myFontSize;
    
    public TextGraphic (String text, double size) { 
        myText = text;
        myFontSize = size;
    }
    
    @Override
    public Node getVisualRepresentation (IGraphicFactory factory) {
        return factory.getVisual(this);
    }
    
    public String getText () {
        return myText;
    }
    
    public double getFontSize () {
        return myFontSize;
    }
	
}
