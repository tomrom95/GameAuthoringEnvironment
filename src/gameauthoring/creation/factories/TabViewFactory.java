// This entire file is part of my masterpiece.
// Dhrumil Patel

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
 * The purpose of this class is to create tab viewer objects requested by the program and provide
 * its associated properties to render a working tab viewer. The factory class generates a factory 
 * to build the tab views required for the authoring environment based on specified queries by 
 * the user. The tab viewer is responsible to rendering its defined tab that holds the UI elements 
 * responsible for activating the elements responsible for the user to interact with and create 
 * meaningful sprites, events, and other objects.
 * 
 * This factory demonstrates my strong grasp of the factory design pattern, use of reflection,
 * resource files, advanced Java, interfaces, generics, an internal util class, and lambda
 * expressions.
 * Each one of the categories is used to serve its respective purpose and works in tandem
 * with the rest of the class and corresponding hierarchy to provide the
 * aforementioned functionality. I use the factory design pattern and reflection (as opposed to enumerate 
 * various tab viewers using an if-else or switch cases). I believe this allows the developers to request the 
 * instance of any tab viewer that exists in the code base. I used a few resource files in this class to store the
 * full class names of the viewers that exist in our codebase, a resource to manage languages, and a resource to 
 * reference a particular tab viewer after creation to build its respective tab. 
 * 
 * 
 * 
 * 
 * @author Dhrumil
 *
 * @param <T> Tab view that represents the GUI components of a tab
 */
public class TabViewFactory<T extends ITabViewer> {

    private static final String IMAGES = "defaults/create_tab_images";
    private static final String TAB_NAMES = "languages/labels";
    private static final String TAB_VIEWER_NAMES = "defaults/create_tabviewers";
    private static final String ERRORS = "defaults/tab_errors";
    private static final String KEY_FINDER = "Order";

    private IGame myGame;
    private ResourceBundle myImages;
    private ResourceBundle myTabs;
    private ResourceBundle myTabViewers;
    private ResourceBundle myErrors;
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
        myErrors = ResourceBundle.getBundle(ERRORS);
        myUIFactory = new BasicUIFactory();
        myTabViewerMap = new HashMap<>();
    }

    /**
     * Reflectively generates the appropriate tab viewers based on the associated resource file
     *
     * @return list of tab viewers
     */
    public List<ITabViewer> createTabViewers () {
        List<ITabViewer> tabViewerList = new ArrayList<>();
        List<String> tabViewerNames = getTabViewerNames();

        for (String tabName : tabViewerNames) {
            ITabViewer tabView;

            try {
                tabView =
                        (ITabViewer) Reflection.createInstance(myTabViewers.getString(tabName),
                                                               myGame);
            }
            catch (ReflectionException reflectionError) {
                String message =
                        String.format(myErrors.getString("REFLECTION_EXCEPTION"),
                                      myTabViewers.getString(tabName), tabName,
                                      reflectionError.getMessage());
                throw new ReflectionException(message);
            }
            catch (ClassCastException classError) {
                String message =
                        String.format(myErrors.getString("CLASS_CAST_EXCEPTION"),
                                      myTabViewers.getString(tabName), tabName,
                                      classError.getMessage());
                throw new ClassCastException(message);

            }
            tabViewerList.add(tabView);
            myTabViewerMap.put(tabName, tabView);
        }

        return tabViewerList;

    }

    /**
     * Generates tabs with its associated view and resources based on resource files
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

    /**
     * Uses the util BundleOperations to convert a resource file into a list of key headers
     * 
     * @return list of key names
     */
    private List<String> getTabViewerNames () {
        return BundleOperations.getPropertyValueAsList(KEY_FINDER, myTabViewers);
    }

}
