package engine;

import engine.aipathing.GameGraphFactory;
import engine.sprite.ISprite;
import util.BoundEdge;
import util.Bounds;
import util.CachingEdgeBitMap;
import util.Coordinate;
import util.IBitMap;
import util.IBoundEdge;
import util.ISampledBitMap;
import util.SampledBitMap;
import util.TimeDuration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will loop through the sprites each in the current running level each
 * update cycle in the game and will use their bounds to create a {@link BitMap} of
 * the current places on the board that are 'obstructed'
 * @author jonathanim
 *
 */
public class ObstructionManager implements IObstructionManager {
    private static final boolean POSITION_OBSTRUCTED = true;
    private IGame myGame;
    private ISampledBitMap myCurrentObstructionMap;

    ObstructionManager (IGame game) {
        myGame = game;
        myCurrentObstructionMap = getBitMapSizedForCurrentGame(getGame());
    }

    @Override
    public void update (TimeDuration duration) {
        myCurrentObstructionMap = parseCurrentGameForObstructions(getGame());
    }
    
    @Override
    public ISampledBitMap getObstructionMap () {
        return myCurrentObstructionMap;
        //return new CachingEdgeBitMap(getObstructionMap());
    }

    private ISampledBitMap parseCurrentGameForObstructions (IGame game) {
        ISampledBitMap obstructionMap = getBitMapSizedForCurrentGame(game);
        game.getLevelManager().getCurrentLevel().getSprites()
                    .stream()
                    .forEach(sprite -> ifObstructsMarkSprite(obstructionMap, sprite));
        //calculateEdges(obstructionMap, game);
        return obstructionMap;
    }

    private void calculateEdges (ISampledBitMap map, IGame game) {
        List<Bounds> allBounds = game.getLevelManager().getCurrentLevel().getSprites()
                .stream()
                .filter(sprite -> sprite.doesObstruct())
                .map(sprite -> sprite.getBounds())
                .collect(Collectors.toList());
        List<IBoundEdge> boundEdges = new ArrayList<>();
        while (!allBounds.isEmpty()) {
            IBoundEdge toAdd = new BoundEdge();
            boundEdges.add(toAdd);
            List<Bounds> toRemove = new ArrayList<>();
            for (Bounds bnd : allBounds) {
                toAdd.addBoundToEdge(bnd);
                toRemove.add(bnd);
            }
            allBounds.removeAll(toRemove);
        }
//        map.setEdges(boundEdges.stream().map(boundEdge -> boundEdge.getEdge())
//                .collect(Collectors.toList()));
//        edgeBorder(map);
        return;
    }

    private void edgeBorder (ISampledBitMap obstructionMap) {
        List<Coordinate> border = new ArrayList<>();
        int xGap = obstructionMap.widthScale();
        int yGap = obstructionMap.heightScale();
        for (int i = -1; i <= obstructionMap.getHeight(); i++) {
            border.add(new Coordinate(-1, i*yGap));
            border.add(new Coordinate(obstructionMap.getWidth(), i*yGap));
        }
        for (int i = -1; i <= obstructionMap.getWidth(); i++) {
            border.add(new Coordinate(i*xGap, -1));
            border.add(new Coordinate(i*xGap, obstructionMap.getHeight()));
        }
        
        //obstructionMap.getEdges().add(border);
        return;
    }
    

    private void ifObstructsMarkSprite (ISampledBitMap map, ISprite sprite) {
        if (sprite.doesObstruct()) {
            markSpriteOnMap(map, sprite);
        }
    }

    private void markSpriteOnMap (ISampledBitMap map, ISprite sprite) {
        Bounds bound = sprite.getBounds();
        int leftX = (int) Math.round(bound.getLeft()) / map.widthScale();
        int rightX = (int) Math.round(bound.getRight())/ map.widthScale();
        int topY = (int) Math.round(bound.getTop())/ map.heightScale();
        int botY = (int) Math.round(bound.getBottom())/ map.heightScale();
        for (int i = leftX; i <= rightX; i++) {
            for (int j = topY; j <= botY; j++) {
                map.set(i, j, POSITION_OBSTRUCTED);
            }
        }
    }

    private IGame getGame () {
        return myGame;
    }

    private ISampledBitMap getBitMapSizedForCurrentGame (IGame game) {
        int gameWidth = game.getGameGridConfig().getGridWidth();
        int gameHeight = game.getGameGridConfig().getGridHeight();
        return new SampledBitMap(gameWidth, gameHeight);
    }

}