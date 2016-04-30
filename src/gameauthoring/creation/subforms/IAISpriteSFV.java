package gameauthoring.creation.subforms;

public interface IAISpriteSFV extends ISubFormView {

    boolean getIsGoal ();

    boolean getIsAI ();

    boolean getIsObstructable ();

    void populateWithData (boolean isObstructable, boolean isGoal, boolean isAI);

}
