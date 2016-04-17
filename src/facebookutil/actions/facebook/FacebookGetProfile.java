package facebookutil.actions.facebook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.GetProfile;
import facebookutil.actions.OAuthSender;
import facebookutil.login.LoginObject;

public class FacebookGetProfile implements GetProfile{
    
    private static final String PROFILE_URL = "https://graph.facebook.com/v2.5/me?fields=id,email";
    private static final String EMAIL_REGEX = "\"email\":\"([^&]+?)\"";
    private static final String ID_REGEX = "\"id\":\"([^&]+?)\"";
    
    private Response myResponse;
    
    public FacebookGetProfile (LoginObject myLoginObject) {
        send(myLoginObject.getService(), myLoginObject.getToken());
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        final OAuthRequest request =
                new OAuthRequest(Verb.GET, PROFILE_URL, service);
        myResponse = OAuthSender.sendRequest(service, token, request);
    }

    @Override
    public String getEmail () {
        return getFirstGroup(EMAIL_REGEX, myResponse.getBody());
    }

    @Override
    public String getUserID () {
        return getFirstGroup(ID_REGEX, myResponse.getBody());
    }
    
    private String getFirstGroup (String pattern, String body) {
        // TODO refactor into a JSON getter class
        Matcher m = Pattern.compile(pattern).matcher(body);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

}
