package splash;

import java.util.Locale;
import java.util.ResourceBundle;
import facebookutil.JavaSocial;
import facebookutil.SocialType;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import library.GameLibrary;


/**
 * This is the main user interface class which contains two buttons "Create game" and
 * "Load existing game".
 * 
 * @TODO: ResourceBundle for unprotected string
 * @TODO: Load Game method
 * @author Jin An
 *
 */
public class MainUserInterface {

    private static final String SPACING_KEY = "spacing";
    private static final String SPLASHTITLEKEY = "splashtitle";
    private static final String LABELS_PATH = "languages/labels";
    private final ResourceBundle mySpecs = ResourceBundle.getBundle("defaults/splashscreen");
    private ResourceBundle myLabels;
    private Stage myStage;
    private BorderPane myLayout = new BorderPane();

    public MainUserInterface () {
        setResourceBundle();
        myLayout.setTop(createLanguageSelector());
        createListener();
        initCenter();
    }

    private void createListener () {
        LocaleManager.getInstance().getCurrentLocaleProperty()
                .addListener(e -> updatedLanguage());
    }

    private void updatedLanguage () {
        setResourceBundle();
        initCenter();
        setTitle();
    }

    private void setResourceBundle () {
        myLabels = ResourceBundle.getBundle(LABELS_PATH, LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
    }

    private void initCenter () {
        myLayout.setCenter(createCenter());
    }

    private Node createLanguageSelector () {
        ObservableList<Locale> locales = FXCollections
                .observableArrayList(LocaleManager.getInstance().getSupportedLocales());
        ComboBox<Locale> selector = new ComboBox<>(locales);
        selector.setValue(LocaleManager.getInstance().getCurrentLocaleProperty().get());
        selector.valueProperty().addListener(event -> updateLocale(selector));
        return selector;
    }

    private void updateLocale (ComboBox<Locale> selector) {
        LocaleManager.getInstance()
                .setCurrentLocale(selector.getSelectionModel().getSelectedItem());
    }

    public void init (Stage s) {
        myStage = s;
        Scene scene = new Scene(myLayout, AuthoringView.WIDTH, AuthoringView.HEIGHT);
        setTitle();
        s.setScene(scene);
        s.show();
    }

    private void setTitle () {
        myStage.setTitle(myLabels.getString(SPLASHTITLEKEY));
    }

    public Node createCenter () {
        VBox root = new VBox(Integer.parseInt(mySpecs.getString(SPACING_KEY)));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(new Label(myLabels.getString(SPLASHTITLEKEY)));
        root.getChildren()
                .add(createButton(myLabels.getString("splashcreategame"), e -> createGame()));
        root.getChildren()
                .add(createButton(myLabels.getString("splashgamelibrary"), e -> launchLibrary()));
        root.getChildren()
                .add(createLogin());
        return root;
    }

    private Node createLogin () {
        HBox box = new HBox(Integer.parseInt(mySpecs.getString(SPACING_KEY)));
        box.setAlignment(Pos.CENTER);
        box.getChildren()
                .add(createButton(myLabels.getString("splashloginfb"), e -> loginWithFacebook()));
        return box;
    }

    private Button createButton (String btnName, EventHandler<ActionEvent> event) {
        Button newButton = new Button(btnName);
        newButton.setOnAction(event);
        return newButton;
    }

    private void createGame () {
        AuthoringView aView = new AuthoringView();
        aView.init(myStage);
    }

    private void launchLibrary () {
        new GameLibrary().init(myStage);
    }

    private void loginWithFacebook () {
        JavaSocial.getInstance().loginUser(SocialType.FACEBOOK);
    }

}
