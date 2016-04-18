package facebookutil.applications;

import java.util.List;
import java.util.ResourceBundle;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.HighScoreBoard;
import facebookutil.ParseHelper;
import facebookutil.actions.facebook.FacebookNotifyUsers;
import facebookutil.login.LoginObject;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public class FacebookApp extends App{
    private static final String APP_URL = "https://graph.facebook.com/oauth/access_token?" +
                                          "&grant_type=client_credentials&client_secret=%s&client_id=%s";
    private static final String TOKEN_REGEX = "access_token=([^&]+)";

    @Override
    public void login () {
        ResourceBundle secrets = ResourceBundle.getBundle("facebookutil/secret");
        final OAuth20Service service = createService(secrets); 
        final OAuthRequest request = createRequest(service, secrets);
        Response response = request.send();
        processResponse(response, secrets, service);
    }

    private void processResponse (Response response, ResourceBundle secrets, OAuth20Service service) {
        LoginObject login = new LoginObject();
        login.setUserID(secrets.getString("facebookId"));
        login.setToken(new OAuth2AccessToken(ParseHelper.getFirstGroup(TOKEN_REGEX, response.getBody())));
        login.setService(service);
        login(login);
    }

    private OAuthRequest createRequest (OAuth20Service service, ResourceBundle secrets) {
        String url = String.format(APP_URL, secrets.getString("facebookSecret"),
                                   secrets.getString("facebookId"));
        return new OAuthRequest(Verb.GET, url, service);
    }

    private OAuth20Service createService (ResourceBundle secrets) {
        return new ServiceBuilder()
                .apiKey(secrets.getString("facebookId"))
                .apiSecret(secrets.getString("facebookSecret"))
                .callback(secrets.getString("callback"))
                .grantType("client_credentials")
                .build(FacebookApi.instance());
    }
    
    

    @Override
    public void notifyUsers (List<IUser> users, String message) {
        FacebookNotifyUsers notify = new FacebookNotifyUsers();
        notify.createNotification(users, message);
        notify.send(getLogin().getService(), getLogin().getToken());
    }

    @Override
    public void customPost (String message, SocialProfile profile) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void HighScoreBoardPost (HighScoreBoard board, SocialProfile profile) {
        // TODO Auto-generated method stub
        
    }

}
