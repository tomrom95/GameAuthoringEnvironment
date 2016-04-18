package facebookutil.login;

import java.util.ResourceBundle;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.JavaSocial;


public class FacebookLogin implements Login {
    private static final String SCOPE = "publish_actions,email";

    private ResourceBundle mySecrets;
    private LoginObject myLoginObject;

    public FacebookLogin () {
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        myLoginObject = new LoginObject();
    }

    @Override
    public void authenticate (JavaSocial social) {
        OAuth20Service service = createService(mySecrets.getString("facebookId"),
                                  mySecrets.getString("facebookSecret"));
        myLoginObject.setService(service);
        createToken(social);
    }

    private OAuth20Service createService (String clientId, String clientSecret) {
        return new ServiceBuilder().apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(mySecrets.getString("callback"))
                .scope(SCOPE)
                .build(FacebookApi.instance());
    }
    
    private void createToken (JavaSocial social) {
        LoginView view = new LoginView(myLoginObject.getService().getAuthorizationUrl());
        view.attachListener(new FacebookListener(view.getEngine(), social, myLoginObject));
    }

}
