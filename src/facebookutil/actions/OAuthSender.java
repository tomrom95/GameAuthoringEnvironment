package facebookutil.actions;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import facebookutil.login.LoginObject;

public class OAuthSender {
    
    public static Response sendRequest(LoginObject login, OAuthRequest request) {
        login.getService().signRequest(login.getToken(), request);
        return request.send();
    }

}
