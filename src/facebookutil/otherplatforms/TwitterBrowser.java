package facebookutil.otherplatforms;

import java.awt.Dimension;
import java.util.ResourceBundle;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;


public class TwitterBrowser {
    public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    private static final String PROTECTED_RESOURCE_URL =
            "https://graph.facebook.com/v2.5/10204226196654701";

    private OAuth1RequestToken twitterToken;
    private Scene myScene;
    private WebView myPage;
    private ResourceBundle mySecrets;

    public TwitterBrowser () {
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        BorderPane root = new BorderPane();
        root.setCenter(makePageDisplay());
        // facebookExample();
        twitterExample();

        myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

    }

    public Scene getScene () {
        return myScene;
    }

    private Node makePageDisplay () {
        myPage = new WebView();
        return myPage;
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
        System.out.println("auth: " + authorizationUrl);
        myPage.getEngine().load(authorizationUrl);

        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");

        String val = "123124";
        // Traditional way to get the response value.
        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        final OAuth1AccessToken accessToken = service.getAccessToken(twitterToken, val);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken +
                           ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("That's it man! Go and build something awesome with ScribeJava! :)");
    }

}
