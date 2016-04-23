package gameauthoring.waves;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.spawnerdef.WaveBlockDefinition;
import engine.definitions.spawnerdef.WaveDefinition;

public class WaveTabController {

    private WaveTabViewer myView; 
    private CreationZone myCreationZone;
    
    public WaveTabController (WaveTabViewer view, CreationZone creationZone) {
        myView = view;
        myCreationZone = creationZone;
        setActions();
    }

    private void setActions () {
        exitMode();
        myCreationZone.setSaveButtonAction(e -> myView.save());
        myView.setWaveDislayAction(e -> transfer());
    }

    private void exitMode () {
        myCreationZone.exitEdit();
        myView.exitEdit();
        myCreationZone.setButtonAction(e -> myView.createWave());    
    }

    private void transfer () {
        if(myView.getWaveSelection()!=null) {
            myView.transfer(getList(myView.getWaveSelection()));
            editMode();
        }
    }

    private void editMode () {
        myCreationZone.enterEdit();
        myCreationZone.setButtonAction(e -> exitMode());  
    }

    private List<WaveBlockDefinition> getList (WaveDefinition waveSelection) {
        return new ArrayList<>(waveSelection.getWaveBlocks());
    }
    
    
}
