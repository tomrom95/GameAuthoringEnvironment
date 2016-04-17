package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.SocialType;
import facebookutil.actions.CustomPost;
import facebookutil.user.IUser;
import facebookutil.user.SocialProfile;

public class FacebookCustomPost implements CustomPost{
    
    private static final String POST_URL = "https://graph.facebook.com/%s/feed";
    
    private OAuthRequest myRequest;

    @Override
    public void createPost (String message, IUser user) {
        System.out.println("USERID : " + getProfile(user).getUserID());
        myRequest = new OAuthRequest(Verb.POST,
                                     String.format(POST_URL, getProfile(user).getUserID()),
                                     getProfile(user).getLogin().getService());
        myRequest.addBodyParameter("message", message);
    }
    
    private SocialProfile getProfile (IUser user) {
        return user.getProfiles().getProfileByType(SocialType.Facebook);
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        service.signRequest(token, myRequest);
        Response response = myRequest.send();
        System.out.println(response.getBody());
    }

}
