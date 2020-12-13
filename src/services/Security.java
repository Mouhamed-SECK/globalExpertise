package services;

import dao.UserDao;
import javafx.collections.ObservableList;
import models.Employes;
import models.User;

/**
 *
 * @author ASUS
 */
public class Security {

    private final UserDao userDao;

    public Security() {
        userDao = new UserDao();
    }

    public Employes logInUser(String login, String Password) {
        return userDao.selectUserByEmailAndPassword(login, Password);
    }

    public ObservableList<User> fetchEmployesList() {
        return userDao.selectAll();
        
      
    }

}
