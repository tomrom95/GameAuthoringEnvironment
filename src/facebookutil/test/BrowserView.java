package facebookutil.test;

import java.awt.Dimension;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;


/**
 * Runs an example browser that can be incorporated into a game to integrate the Social utility
 *
 */
public class BrowserView {

    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    private static final String GOOGLE_PROTECTED_RESOURCE_URL =
            "https://www.googleapis.com/plus/v1/people/me";

    private static final String CALLBACK_URL =
            "https://duke.edu/";

    private OAuth20Service service;
    private OAuth1RequestToken twitterToken;
    private Scene myScene;
    private WebView myPage;
    private ResourceBundle mySecrets;

    public BrowserView () {
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        BorderPane root = new BorderPane();
        root.setCenter(makePageDisplay());
        // facebookExample();
        googleExample();

        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

    }

    public Scene getScene () {
        return myScene;
    }

    private Node makePageDisplay () {
        myPage = new WebView();
        myPage.getEngine().getLoadWorker().stateProperty().addListener(new GoogleLinkListener());
        return myPage;
    }

    private class GoogleLinkListener implements ChangeListener<State> {

        @Override
        public void changed (ObservableValue<? extends State> ov, State oldState, State newState) {
            if (newState == Worker.State.SUCCEEDED) {
                String newURL = myPage.getEngine().getLocation();
                System.out.println("new URL" + newURL);
                Pattern pattern = Pattern.compile("code=([^&]+)");
                Matcher m = pattern.matcher(newURL);
                if (m.find()) {

                    System.out.println(m.group(1));
                    OAuth2AccessToken accessToken = service.getAccessToken(m.group(1));
                    System.out.println("Got the Access Token!");
                    System.out.println("(if your curious it looks like this: " + accessToken +
                                       ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
                    System.out.println(accessToken.getAccessToken());

                    System.out.println(accessToken.getAccessToken());

                    // Now let's go and ask for a protected resource!
                    System.out.println("Now we're going to access a protected resource...");
                    final OAuthRequest request =
                            new OAuthRequest(Verb.GET, GOOGLE_PROTECTED_RESOURCE_URL, service);
                    service.signRequest(accessToken, request);
                    final Response response = request.send();
                    System.out.println(request.getBodyContents());
                    System.out.println("Got it! Lets see what we found...");
                    System.out.println();
                    System.out.println(response.getCode());
                    System.out.println(response.getBody());
                    m = Pattern.compile("\"email\":\"([^&]+)\"").matcher(response.getBody());
                    if (m.find()) {
                        System.out.println(m.group(1));
                    }

                    // SEND A NOTIFICATION
                    OAuthRequest nextRequest =
                            new OAuthRequest(Verb.POST,
                                             "https://www.googleapis.com/plusDomains/v1/people/me/activities",
                                             service);
                    // String message = "Let's make tower defense!";
                    // nextRequest.addBodyParameter("object",);
                    // nextRequest.addBodyParameter("ids", "10204226196654701");
                    service.signRequest(accessToken, nextRequest);

                    Response nextResponse = nextRequest.send();
                    System.out.println("here");
                    System.out.println(nextRequest.getBodyContents());
                    System.out.println(nextResponse.getCode());
                    String responseBody = nextResponse.getBody();
                    System.out.println(responseBody);
                    //
                    //

                }
                else {

                }
            }
        }
    }

    public void facebookExample () {
        // Replace these with your client id and secret
        final String clientId = mySecrets.getString("facebookId");
        final String clientSecret = mySecrets.getString("facebookSecret");
        service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(CALLBACK_URL)
                // .grantType("client_credentials")
                .scope("publish_actions,email,public_profile")
                // .scope("publish_actions")
                .build(FacebookApi.instance());

        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String authorizationUrl = service.getAuthorizationUrl();
        myPage.getEngine().load(authorizationUrl);
    }

    public void twitterExample () {
        final String clientId = mySecrets.getString("twitterId");
        final String clientSecret = mySecrets.getString("twitterSecret");

        final OAuth10aService service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .grantType("client_credentials")
                .build(TwitterApi.instance());

        System.out.println("=== Twitter's OAuth Workflow ===");
        System.out.println();

        twitterToken = service.getRequestToken();
        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        System.out.println("request: " + twitterToken.toString());
        final String authorizationUrl = service.getAuthorizationUrl(twitterToken);
        myPage.getEngine().load(authorizationUrl);
    }

    public void googleExample () {
        // Replace these with your client id and secret
        final String clientId = mySecrets.getString("googleId");
        final String clientSecret = mySecrets.getString("googleSecret");
        service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(CALLBACK_URL)
                // .scope("https://www.googleapis.com/auth/plus.login ")
                .scope("https://www.googleapis.com/auth/plus.circles.write https://www.googleapis.com/auth/plus.circles.read https://www.googleapis.com/auth/plus.stream.write https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/plus.stream.read")

                .build(GoogleApi20.instance());

        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String authorizationUrl = service.getAuthorizationUrl();
        myPage.getEngine().load(authorizationUrl);
    }
}
