package gameauthoring.characters;

public abstract class TempProfileSubFormView extends SubFormView {
    
    private int myNameInd = 0;
    private int myImageInd = 1;
    private int myDescriptionInd = 2;
    
    protected abstract void init();

    public int getMyNameInd (){
        return this.myNameInd;
    }

    public int getMyImageInd (){
        return this.myImageInd;
    }

    public int getMyDescriptionInd (){
        return this.myDescriptionInd;
    }

}
