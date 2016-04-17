package facebookutil.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.scribejava.core.model.OAuth2AccessToken;
import facebookutil.JavaSocial;
import facebookutil.SocialType;
import facebookutil.actions.GetProfile;
import facebookutil.actions.facebook.FacebookGetProfile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;

public class FacebookListener implements ChangeListener<State> {
    private static final String CODE_REGEX = "code=([^&]+)";
    
    private WebEngine myEngine;
    private JavaSocial mySocial;
    private LoginObject myLoginObject;

    public FacebookListener (WebEngine engine, JavaSocial social, LoginObject login) {
        myEngine = engine;
        mySocial = social;
        myLoginObject = login;
    }

    @Override
    public void changed (ObservableValue<? extends State> ov, State oldState, State newState) {
        if (newState == Worker.State.SUCCEEDED) {
            String newURL = myEngine.getLocation();
            checkForLogin(newURL);
            
        }
    }

    private void checkForLogin (String newURL) {
        Matcher m = Pattern.compile(CODE_REGEX).matcher(newURL);
        if (m.find()) {
            OAuth2AccessToken token = myLoginObject.getService().getAccessToken(m.group(1));
            myLoginObject.setToken(token);
            findProfile();
            login ();
        }
    }

    private void login () {
        mySocial.loginUser(SocialType.Facebook, myLoginObject);
    }

    private void findProfile () {
        GetProfile getter = new FacebookGetProfile (myLoginObject);
        myLoginObject.setEmail(getter.getEmail());
        myLoginObject.setUserID(getter.getUserID());
    }
};
