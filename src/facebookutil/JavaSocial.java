package facebookutil;

import java.util.HashSet;
import java.util.Set;
import facebookutil.login.Login;
import facebookutil.user.IUser;
import facebookutil.user.User;

public class JavaSocial implements IJavaSocial {
    
    private Set<IUser> myUsers;
    private HighScoreBoard myHighScores;
    
    public JavaSocial () {
        //TODO load users from file
        myUsers = new HashSet<>();
        myHighScores = new HighScoreBoard ();
    }

    @Override
    public Set<IUser> getUsers () {
        return new HashSet<IUser>(myUsers);
    }

    @Override
    public IUser getUserByEmail (String email) {
        for (IUser user: myUsers) {
            if (user.getUserEmail() == email) {
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
    public void loginUser (String email, SocialType type, Login login) {
        IUser user = getUserByEmail(email);
        if (user == null) {
            user = createNewUser(email);
        }
        user.login(type, login);
    }

    @Override
    public IUser createNewUser (String email) {
        IUser newUser = new User(email);
        myUsers.add(newUser);
        return newUser;
    }

}
