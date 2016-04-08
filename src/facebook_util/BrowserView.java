package facebook_util;

import java.awt.Dimension;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class BrowserView {
    
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.5/me";
    private static final String CALLBACK_URL = "https://github.com/duke-compsci308-spring2016/voogasalad_GitDepends/";
    
    private OAuth20Service service;
    private Scene myScene;
    private WebView myPage;
    private ResourceBundle mySecrets;

    public BrowserView () {
        mySecrets = ResourceBundle.getBundle("facebook_util/secret");
        BorderPane root = new BorderPane();
        root.setCenter(makePageDisplay());
        facebookExample();
        
        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        
    }


    public Scene getScene () {
        return myScene;
    }

    private Node makePageDisplay () {
        myPage = new WebView();
        myPage.getEngine().getLoadWorker().stateProperty().addListener(new LinkListener());
        return myPage;
    }

    private class LinkListener implements ChangeListener<State> {

        @Override
        public void changed (ObservableValue<? extends State> ov, State oldState, State newState) {
            if (newState == Worker.State.SUCCEEDED) {
                String newURL = myPage.getEngine().getLocation();
                System.out.println(newURL);
                Pattern p = Pattern.compile("code=([^&]+)");
                Matcher m = p.matcher(newURL);
                if (m.find()) {

                    System.out.println(m.group(1));
                    final OAuth2AccessToken accessToken = service.getAccessToken(m.group(1));
                    System.out.println("Got the Access Token!");
                    System.out.println("(if your curious it looks like this: " + accessToken +
                                       ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
                    System.out.println();

                    // Now let's go and ask for a protected resource!
                    System.out.println("Now we're going to access a protected resource...");
                    final OAuthRequest request =
                            new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
                    service.signRequest(accessToken, request);
                    final Response response = request.send();
                    System.out.println("Got it! Lets see what we found...");
                    System.out.println();
                    System.out.println(response.getCode());
                    System.out.println(response.getBody());

                }
                else {
                    
                }
            }
        }
    };

    public void facebookExample () {
        // Replace these with your client id and secret
        final String clientId = mySecrets.getString("clientId");
        final String clientSecret = mySecrets.getString("clientSecret");
        final String secretState = "secret" + new Random().nextInt(999_999);
        service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .state(secretState)
                .callback(CALLBACK_URL)
                .build(FacebookApi.instance());

        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String authorizationUrl = service.getAuthorizationUrl();
        myPage.getEngine().load(authorizationUrl);
    }
}
