package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.tabs.ITabViewer;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.control.Tab;
import splash.LocaleManager;
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
    private static final String TAB_NAMES = "languages/labels";
    private static final String TAB_VIEWER_NAMES = "defaults/create_tabviewers";

    private IGame myGame;
    private ResourceBundle myImages;
    private ResourceBundle myTabs;
    private ResourceBundle myTabViewers;
    private BasicUIFactory myUIFactory;
    private Map<String, ITabViewer> myTabViewerMap;

    public TabViewFactory (IGame game) {
        myGame = game;
        myImages = ResourceBundle.getBundle(IMAGES);
        myTabs =
                ResourceBundle
                        .getBundle(TAB_NAMES,
                                   LocaleManager.getInstance().getCurrentLocaleProperty().get());
        myTabViewers = ResourceBundle.getBundle(TAB_VIEWER_NAMES);
        myUIFactory = new BasicUIFactory();
        myTabViewerMap = new HashMap<>();
    }

    /**
     * Reflectively generates the appropriate tab viewers
     *
     * @return list of tab viewers
     */
    public List<ITabViewer> createTabViewers () {
        List<ITabViewer> tabViewerList = new ArrayList<>();
        List<String> tabViewerNames = getTabViewerNames();
        myTabViewerMap = new HashMap<>();

        for (String tabName : tabViewerNames) {
            ITabViewer tabView;

            try {
                tabView =
                        (ITabViewer) Reflection.createInstance(myTabViewers.getString(tabName),
                                                               myGame);
            }
            catch (ReflectionException e) {
                String message =
                        String.format("Reflection exception in TabViewFactory when creating tab %s from key %s: \n%s",
                                      myTabViewers.getString(tabName), tabName, e.getMessage());
                throw new ReflectionException(message);
            }
            catch (ClassCastException e) {
                String message =
                        String.format("Class cast exception in TabViewFactory when creating tab %s from key %s: \n%s",
                                      myTabViewers.getString(tabName), tabName, e.getMessage());
                throw new ClassCastException(message);

            }
            tabViewerList.add(tabView);
            myTabViewerMap.put(tabName, tabView);
        }

        return tabViewerList;

    }

    /**
     * Generates tabs with its associated view and resources
     *
     * @return list of available tabs to render
     */
    public List<Tab> createTabs () {
        List<Tab> tabList = new ArrayList<>();

        getTabViewerNames().stream().forEach(tabName -> tabList.add(myUIFactory
                .createTabGraphic(myUIFactory.makeImageDisplay(myImages.getString(tabName),
                                                               myTabs.getString(tabName)),
                                  false, myTabViewerMap.get(tabName)
                                          .draw())));

        return tabList;
    }

    private List<String> getTabViewerNames () {
        List<String> tabViewerNames =
                BundleOperations.getPropertyValueAsList("Order", myTabViewers);
        return tabViewerNames;
    }

}
