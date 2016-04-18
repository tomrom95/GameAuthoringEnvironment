package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.ParseHelper;
import facebookutil.actions.GetProfile;
import facebookutil.actions.OAuthSender;
import facebookutil.login.LoginObject;

public class FacebookGetProfile implements GetProfile{
    
    private static final String PROFILE_URL = "https://graph.facebook.com/v2.5/me?fields=id,email,name";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String NAME = "name";
    
    private Response myResponse;
    
    public FacebookGetProfile (LoginObject myLoginObject) {
        send(myLoginObject.getService(), myLoginObject.getToken());
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        final OAuthRequest request =
                new OAuthRequest(Verb.GET, PROFILE_URL, service);
        myResponse = OAuthSender.sendRequest(service, token, request);
        System.out.println(myResponse.getBody());
    }

    @Override
    public String getEmail () {
        String email = ParseHelper.JSONParse(EMAIL, myResponse.getBody());
        if (email == null) {
            return getName ();
        }
        return email;
    }

    private String getName () {
        String name = ParseHelper.JSONParse(NAME, myResponse.getBody());
        if (name == null) {
            //TODO throw errors
            System.out.println("NO INFORMATION FOR THIS USER");
            return null;
        }
        return name.replace(" ", "");
    }

    @Override
    public String getUserID () {
        return ParseHelper.JSONParse(ID, myResponse.getBody());
    }

}
