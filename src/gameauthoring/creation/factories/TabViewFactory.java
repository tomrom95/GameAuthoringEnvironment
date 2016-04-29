package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.tabs.ITabViewer;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.control.Tab;
import util.BundleOperations;


/**
 * Creating a factory to generate the tab views required for the
 * authoring environment
 * 
 * @author Dhrumil
 *
 */
public class TabViewFactory<T extends ITabViewer> {

    private static final String IMAGES = "defaults/create_tab_images";
    private static final String TAB_NAMES = "defaults/create_tabs";
    private static final String TAB_VIEWER_NAMES = "defaults/create_tabviewers";

    private IGame myGame;
    private ResourceBundle myImages;
    private ResourceBundle myTabs;
    private ResourceBundle myTabViewers;
    private BasicUIFactory myUIFactory;

    public TabViewFactory (IGame game) {
        myGame = game;
        myImages = ResourceBundle.getBundle(IMAGES);
        myTabs = ResourceBundle.getBundle(TAB_NAMES);
        myTabViewers = ResourceBundle.getBundle(TAB_VIEWER_NAMES);
        myUIFactory = new BasicUIFactory();
    }

    private ITabViewer createTabViewer (String tabViewerName) {
        return (ITabViewer) Reflection.createInstance(tabViewerName, myGame);

    }

    /**
     * Generates tabs with its associated view and resources
     * 
     * @return list of available tabs to render
     */
    public List<Tab> createTabs () {
        List<Tab> tabList = new ArrayList<>();
        List<String> tabViewerNames =
                BundleOperations.getPropertyValueAsList("Order", myTabViewers);

        for (String tabName : tabViewerNames) {
            tabList.add(myUIFactory
                    .createTabGraphic(myUIFactory.makeImageDisplay(myImages.getString(tabName),
                                                                   myTabs.getString(tabName)),
                                      false, createTabViewer(tabName)
                                              .draw()));
        }

        return tabList;
    }

}
