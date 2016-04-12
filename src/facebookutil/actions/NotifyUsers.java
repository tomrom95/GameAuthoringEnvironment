package facebookutil.actions;

import java.util.List;
import facebookutil.IUser;

public interface NotifyUsers extends SocialAction {

    public void createNotification (List<IUser> users, String message);
    
}
