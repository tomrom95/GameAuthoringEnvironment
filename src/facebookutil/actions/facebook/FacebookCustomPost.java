package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.CustomPost;
import facebookutil.user.profiles.SocialProfile;

public class FacebookCustomPost implements CustomPost{
    
    private static final String POST_URL = "https://graph.facebook.com/%s/feed";
    
    private OAuthRequest myRequest;

    @Override
    public void createPost (String message, SocialProfile profile) {
        myRequest = new OAuthRequest(Verb.POST,
                                     String.format(POST_URL, profile.getID()),
                                     profile.getLogin().getService());
        myRequest.addBodyParameter("message", message);
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        service.signRequest(token, myRequest);
        Response response = myRequest.send();
        System.out.println(response.getBody());
    }

}
