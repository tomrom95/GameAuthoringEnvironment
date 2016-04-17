package facebookutil.actions;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;

public class OAuthSender {
    
    public static Response sendRequest(OAuth20Service service, OAuth2AccessToken token,
                                       OAuthRequest request) {
        service.signRequest(token, request);
        return request.send();
    }

}
