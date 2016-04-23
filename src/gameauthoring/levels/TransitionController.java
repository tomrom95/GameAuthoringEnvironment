package gameauthoring.levels;

import engine.INextLevelManager;


public class TransitionController {

    private TransitionView myView;
    private INextLevelManager myNextLevelManager;

    public TransitionController (TransitionView transitionView, INextLevelManager iNextLevelManager) {
        myView = transitionView;
        myNextLevelManager = iNextLevelManager;
        setAction();
    }

    private void setAction () {
        myView.setWinAction(e -> myNextLevelManager.setWinLevel(myView.getWinSelection()));
        myView.setLoseAction(e -> myNextLevelManager.setLoseLevel(myView.getLoseSelection()));
    }
    
    

}
