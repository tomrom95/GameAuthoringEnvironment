package facebookutil.applications;

import java.util.HashMap;
import java.util.Map;
import facebookutil.SocialType;

public class AppMap {
    
    private Map<SocialType, App> myMap;
    
    public AppMap () {
        myMap = new HashMap<>();
    }
    
    public void loginApps () {
        for(SocialType type: SocialType.values()) {
            App app = type.getApp();
            app.login();
            myMap.put(type, app);
        }
    }
    
    public App getAppByType (SocialType type) {
        return myMap.get(type);
    }

}
