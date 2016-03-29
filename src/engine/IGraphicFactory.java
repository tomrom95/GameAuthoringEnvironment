package engine;

import javafx.scene.Node;
import javafx.scene.image.Image;
import util.Block;
import util.TextGraphic;

public interface IGraphicFactory {

	Node getVisual (Block block);
	
	Node getVisual (Image image);
	
	Node getVisual (TextGraphic text);
	
}
