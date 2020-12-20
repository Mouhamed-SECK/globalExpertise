/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;
import models.Employes;
import models.User;

/**
 *
 * @author Mouhamed SECK
 */
public class UserDao implements IDao<User> {

    public static enum Type {
        CUSTOMER, EMPLOYES
    }

    private final String SQL_SELECT_ALL_CUSTOMER = "SELECT * FROM `user` WHERE type LIKE 'CUSTOMER'";
    private final String SQL_INSERT_CUSTOMER = "INSERT INTO `user` (`name`, `firstname`, `email`, `cni`, `phoneNumber`, `type`) VALUES (?, ?, ?, ?, ?, ?)";

    private final String SQL_SELECT_ALL_EMPLOYES = "SELECT * FROM `user` WHERE type LIKE 'EMPLOYES'";
    private final String SQL_INSERT_EMPLOYES = "INSERT INTO `user` (`name`, `firstname`, `email`, `matricule`, `login`,`password`, `departement`, `roles`, `image`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT name, firstname, departement, roles, matricule FROM `user` WHERE email=? and password=? ";

    private final String SQL_DELETE = "DELETE FROM `user` WHERE userId=?";

    private final Mysql mysql;

    private Type typeOfSelect;

    public UserDao() {
        mysql = new Mysql();
    }

    public Type getTypeOfSelect() {
        return typeOfSelect;
    }

    public void setTypeOfSelect(Type typeOfSelect) {
        this.typeOfSelect = typeOfSelect;
    }

    @Override
    public User add(User user) {
        mysql.getConnection();

        if (user instanceof Customer) {
            mysql.initPS(SQL_INSERT_CUSTOMER);
        } else {
            mysql.initPS(SQL_INSERT_EMPLOYES);
        }

        PreparedStatement psmt = mysql.getPstm();
        try {
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getFirstname());
            psmt.setString(3, user.getEmail());

            if (user instanceof Customer) {
                psmt.setString(4, ((Customer) user).getCni());
                psmt.setString(5, ((Customer) user).getPhoneNumber());
                psmt.setString(6, Type.CUSTOMER.name());

            } else {
                psmt.setString(4, ((Employes) user).getMatricule());
                psmt.setString(5, ((Employes) user).getLogin());
                psmt.setString(6, ((Employes) user).getPassword());
                psmt.setString(7, ((Employes) user).getDepartement());
                psmt.setString(8, ((Employes) user).getRole());

                File avartar = ((Employes) user).getAvatar();
                FileInputStream fis = new FileInputStream(avartar);
                psmt.setBinaryStream(9, (InputStream) fis, (int) avartar.length());

                psmt.setString(10, Type.EMPLOYES.name());

            }
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setUserId(id);
            }

        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return user;
    }

    @Override

    public ObservableList<User> selectAll() {
        ObservableList<User> users = FXCollections.observableArrayList();
        mysql.getConnection();
        if (typeOfSelect == Type.CUSTOMER) {
            mysql.initPS(SQL_SELECT_ALL_CUSTOMER);
        } else {
            mysql.initPS(SQL_SELECT_ALL_EMPLOYES);
        }

        PreparedStatement ps = mysql.getPstm();
        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = null;

                if (rs.getString("type").compareTo("CUSTOMER") == 0) {
                    user = new Customer();
                     ((Customer) user).setUserId(rs.getInt("userId"));
                    ((Customer) user).setCni(rs.getString("cni"));
                    ((Customer) user).setPhoneNumber(rs.getString("phoneNumber"));
                    AddressDao addressDao = new AddressDao();
                    addressDao.setIdCustomer(user.getUserId());
                    ((Customer) user).setAddresses(addressDao.selectAll());
                } else {
                    user = new Employes();
                    ((Employes) user).setDepartement(rs.getString("departement"));
                    ((Employes) user).setMatricule(rs.getString("matricule"));
                    ((Employes) user).setRole(rs.getString("roles"));
                }
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setFirstname(rs.getString("firstname"));
                user.setEmail(rs.getString("email"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }
        return users;
    }

    public Employes selectUserByEmailAndPassword(String login, String password) {
        mysql.getConnection();
        mysql.initPS(SELECT_USER_BY_LOGIN_PASSWORD);
        PreparedStatement psmt = mysql.getPstm();
        Employes employes = null;
        try {

            psmt.setString(1, login);
            psmt.setString(2, password);
            ResultSet rs = mysql.executeSelect();

            if (rs.next()) {
                employes = new Employes();
                employes.setMatricule(rs.getString("matricule"));
                employes.setName(rs.getString("name"));
                employes.setFirstname(rs.getString("firstName"));
                employes.setDepartement(rs.getString("departement"));
                employes.setRole(rs.getString("roles"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return employes;
    }

    public void delete(int id) {
        mysql.getConnection();

        mysql.initPS(SQL_DELETE);
        PreparedStatement psmt = mysql.getPstm();

        try {
            psmt.setInt(1, id);
            psmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

    }

}
