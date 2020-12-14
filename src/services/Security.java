package services;

import dao.UserDao;
import java.io.File;
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
    
    public User registerEmployes(String name, String firtsName, String email, String departement, String role, File avatar) {
        Employes employes = new Employes(name, departement,role, name, firtsName, email, avatar);
        return userDao.add(employes);  
    }

}
