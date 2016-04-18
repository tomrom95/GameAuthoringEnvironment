package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.Challenge;
import facebookutil.user.profiles.UserProfile;

public class FacebookChallenge implements Challenge{
    private static final String CHALLENGE_URL = "https://graph.facebook.com/%s/apprequests";
    
    private OAuthRequest myRequest;

    @Override
    public void createChallenge (UserProfile source, UserProfile target, String message) {
        myRequest = new OAuthRequest(Verb.POST,
                                 String.format(CHALLENGE_URL, target.getID()),
                                 source.getLogin().getService());
        myRequest.addBodyParameter("message", message);
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        service.signRequest(token, myRequest);
        Response response = myRequest.send();
        System.out.println(response.getBody());
    }

}
