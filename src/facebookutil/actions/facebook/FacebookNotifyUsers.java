package facebookutil.actions.facebook;

import java.util.ArrayList;
import java.util.List;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.SocialType;
import facebookutil.actions.NotifyUsers;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

public class FacebookNotifyUsers implements NotifyUsers {
    private static final String REQUEST_URL = "https://graph.facebook.com/%s/notifications";
    
    private List<String> myRequests = new ArrayList<>();
    private String myMessage;

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        myRequests.stream()
                  .forEach(s -> sendRequest(service, token, s));
    }

    private void sendRequest (OAuth20Service service, OAuth2AccessToken token, String url) {
        OAuthRequest request =
                new OAuthRequest(Verb.POST, url,service);
        request.addBodyParameter("template", myMessage);
        service.signRequest(token, request);
        System.out.println(request.send());
    }

    @Override
    public void createNotification (List<IUser> users, String message) {
        myMessage = message;
        users.stream().forEach(u -> notify(u, message) );
    }

    private void notify (IUser user, String message) {
        SocialProfile profile = user.getProfiles().getProfileByType(SocialType.FACEBOOK);
        if (profile == null ) return;
        createRequest(profile.getID(), message);
        
    }

    private void createRequest (String id, String message) {
        myRequests.add(String.format(REQUEST_URL, id));
    }

}
