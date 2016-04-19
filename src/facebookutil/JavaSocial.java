package facebookutil;

import java.util.ArrayList;
import java.util.List;
import facebookutil.applications.AppMap;
import facebookutil.login.LoginUser;
import facebookutil.login.LoginObject;
import facebookutil.user.Email;
import facebookutil.user.IUser;
import facebookutil.user.User;

/**
 * Class that implements the main java social interface.
 * Loads previous users into a list of current users and allows people
 * to log in, record scores, and post.
 * @author Tommy
 *
 */
public class JavaSocial implements IJavaSocial {
    
    private List<IUser> myUsers;
    private HighScoreBoard myHighScores;
    private IUser activeUser;
    private AppMap myApps;
    
    public JavaSocial () {
        myUsers = loadUsers();
        myHighScores = new HighScoreBoard ();
        myApps = new AppMap();
        myApps.loginApps();
    }

    /**
     * Loads users to list from the XML reader
     * @return
     */
    private List<IUser> loadUsers () {
        UserReader reader = new UserReader ();
        return reader.getUsers();
    }

    @Override
    public List<IUser> getUsers () {
        return new ArrayList<>(myUsers);
    }

    @Override
    public IUser getUserByEmail (Email email) {
        for (IUser user: myUsers) {
            if (user.getUserEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public HighScoreBoard getHighScoreBoard () {
        return myHighScores;
    }

    @Override
    public void loginUser (SocialType type, LoginObject login) {
        IUser user = getUserByEmail(login.getEmail());
        if (user == null) {
            user = createNewUser(login.getEmail());
        } else {
            System.out.println("User exists");
        }
        user.login(type, login);
        activeUser = user;
    }
    
    @Override
    public void loginUser (SocialType type) {
        LoginUser login = type.getLogin();
        login.authenticate(this);
    }

    @Override
    public IUser createNewUser (Email email) {
        System.out.println("Creating new User");
        IUser newUser = new User(email);
        myUsers.add(newUser);
        return newUser;
    }

    /**
     * Gets the current user of the social environment
     * @return
     */
    public IUser getActiveUser () {
        return activeUser;
    }
    
    /**
     * Gets the applications associated with the social app
     * @return
     */
    public AppMap getApplications () {
        return myApps;
    }

    @Override
    public void saveUsers () {
        UserWriter writer = new UserWriter();
        writer.write(getUsers());
    }


}