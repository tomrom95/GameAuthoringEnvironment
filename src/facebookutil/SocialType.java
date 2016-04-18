package facebookutil;

import java.lang.reflect.InvocationTargetException;
import facebookutil.applications.App;
import facebookutil.applications.FacebookApp;
import facebookutil.applications.LocalApp;
import facebookutil.applications.TwitterApp;
import facebookutil.login.FacebookLogin;
import facebookutil.login.LocalLogin;
import facebookutil.login.LoginUser;
import facebookutil.login.TwitterLogin;
import facebookutil.user.profiles.FacebookProfile;
import facebookutil.user.profiles.LocalProfile;
import facebookutil.user.profiles.UserProfile;
import facebookutil.user.profiles.TwitterProfile;

public enum SocialType {
    Facebook (FacebookLogin.class, FacebookProfile.class, FacebookApp.class),
    Twitter (TwitterLogin.class, TwitterProfile.class, TwitterApp.class),
    Local (LocalLogin.class, LocalProfile.class, LocalApp.class);
    
    private Class<? extends LoginUser> myLogin;
    private Class<? extends UserProfile> myProfile;
    private Class<? extends App> myApp;
    
    SocialType (Class<? extends LoginUser> loginClass, Class<? extends UserProfile> profileClass,
                Class<? extends App> appClass) {
        myLogin = loginClass;
        myProfile = profileClass;
        myApp = appClass;
    }
    
    public LoginUser getLogin () {
        try {
            return myLogin.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
    
    public UserProfile getProfile (String userID) {
        try {
            return myProfile.getDeclaredConstructor(String.class).newInstance(userID);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            return null;
        }
    }
    
    public App getApp () {
        try {
            return myApp.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

}
