package services;

import dao.AddressDao;
import dao.UserDao;
import java.io.File;
import javafx.collections.ObservableList;
import models.Address;
import models.Customer;
import models.Employes;
import models.User;

/**
 *
 * @author ASUS
 */
public class Security {

    private final UserDao userDao;
    private final AddressDao addressDao;

    public Security() {
        userDao = new UserDao();
        addressDao = new AddressDao();
    }

    public Employes logInUser(String login, String Password) {
        return userDao.selectUserByEmailAndPassword(login, Password);
    }

    public ObservableList<User> fetchEmployesList() {
        return userDao.selectAll();
    }
    
    public  ObservableList<User> fetchCustomerList() {
         userDao.setTypeOfSelect(UserDao.Type.CUSTOMER);
         return userDao.selectAll();
    }

    public User registerEmployes(String name, String firtsName, String email, String departement, String role, File avatar) {
        Employes employes = new Employes(name, departement, role, name, firtsName, email, avatar);
        return userDao.add(employes);
    }

    public User registerClient(String name, String firtsName, String email, String cni, String numberTel, String city, String loaction, String district) {
        Customer customer = new Customer(numberTel, cni, name, firtsName, email);
        userDao.setTypeOfSelect(UserDao.Type.CUSTOMER);
        User u = userDao.add(customer);
        Address address = new Address(city, loaction, district, u);
        address = addressDao.add(address);
        
        ((Customer)u).addAdress(address);
        return u;
    }

    public void deleteEmployes(int id) {
        userDao.delete(id);
    }

}
