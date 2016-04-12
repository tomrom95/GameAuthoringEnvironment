package facebook_util.actions;

import java.util.List;
import facebook_util.IUser;

public interface NotifyUsers extends SocialAction {

    public void createNotification (List<IUser> users, String message);
    
}
