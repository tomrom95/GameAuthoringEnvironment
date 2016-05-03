package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import facebookutil.actions.CustomPost;
import facebookutil.actions.OAuthAction;
import facebookutil.user.profiles.SocialProfile;


public class FacebookCustomPost extends OAuthAction implements CustomPost {

    private static final String POST_URL = "https://graph.facebook.com/%s/feed";

    @Override
    public void createPost (String message, SocialProfile profile) {
        OAuthRequest myRequest = new OAuthRequest(Verb.POST,
                                                  String.format(POST_URL, profile.getID()),
                                                  profile.getLogin().getService());
        myRequest.addBodyParameter("message", message);
        setRequest(myRequest);
    }

}
