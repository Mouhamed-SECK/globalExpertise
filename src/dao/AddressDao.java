/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Address;
import models.User;

/**
 *
 * @author abdel
 */
public class AddressDao implements IDao<Address> {

    private final String SQL_SELECT_ALL ="select user.userId as idCustomer, address.addressId , firstname, name, city, location, district from `address` INNER JOIN user ON address.idCustomer = user.userId WHERE user.userId = ?";
    private final String SQL_INSERT ="INSERT INTO `address` (`city`,`location`, `district`,`idCustomer`) VALUES (?,?,?,?)";
    private final Mysql mysql;
    
    private int idCustomer;
    
    public AddressDao() {
        mysql = new Mysql();
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    
    
    @Override
    public Address add(Address address) {
         mysql.getConnection();
        
        mysql.initPS(SQL_INSERT);
        
        PreparedStatement psmt = mysql.getPstm();
        try {
            psmt.setString(1, address.getCity());
            psmt.setString(2, address.getLocation());
            psmt.setString(3, address.getDistrict());
            psmt.setInt(4, address.getAddressCustomer().getUserId());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                int addressId = rs.getInt(1);
                address.setAddressId(addressId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return address; 
    }

    @Override
    public ObservableList<Address> selectAll() {
        
       ObservableList<Address> addresses = FXCollections.observableArrayList();
        mysql.getConnection();
        
        mysql.initPS(SQL_SELECT_ALL);
  
        PreparedStatement ps = mysql.getPstm();
        try {
            ps.setInt(1, idCustomer);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Address address = new Address();
                address.setAddressId(rs.getInt("addressId"));
                address.setCity(rs.getString("city"));
                address.setLocation(rs.getString("location"));
                address.setDistrict(rs.getString("district"));
              //  address.getAddressCustomer().setUserId(rs.getInt("idCustomer"));
                addresses.add(address);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }
        return addresses;
       
    }
    
    
}
