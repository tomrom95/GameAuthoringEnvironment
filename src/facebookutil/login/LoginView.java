package facebookutil.login;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LoginView {
    private static final double BROWSER_SIZE = 700;
    
    private WebView myPage;
    
    public LoginView (String startURL) {
        myPage = new WebView();
        startPage(myPage, startURL);
        openStage(myPage);
    }

    private void openStage (WebView myPage) {
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        pane.setCenter(myPage);
        stage.setScene(new Scene (pane, BROWSER_SIZE, BROWSER_SIZE));
        stage.show();
    }

    public void attachListener (ChangeListener<State> listener) {
        myPage.getEngine().getLoadWorker().stateProperty().addListener(listener);
    }

    private void startPage (WebView page, String startURL) {
        page.getEngine().load(startURL);
    }
    
    public WebEngine getEngine () {
        return myPage.getEngine();
    }
}
