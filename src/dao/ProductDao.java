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
import models.Product;

/**
 *
 * @author abdel
 */
public class ProductDao implements IDao<Product> {
    private final String SQL_SELECT_ALL ="SELECT * FROM `product`";
    private final String SQL_INSERT ="INSERT INTO `product` (`code`,`name`, `price`,`quantityInStock`, `categoryId`) VALUES (?,?,?,?,?)";
    private final Mysql mysql;
    
    public ProductDao() {
        mysql = new Mysql();
    }

    @Override
    public Product add(Product product) {
        mysql.getConnection();
        
        mysql.initPS(SQL_INSERT);
        
        PreparedStatement psmt = mysql.getPstm();
        try {
            psmt.setString(1, product.getCode());
            psmt.setString(2, product.getName());
            psmt.setDouble(3, product.getPrice());
            psmt.setDouble(4, product.getQuantityInStock());
            psmt.setInt(5, product.getProductCategory().getCategoryId());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                product.setProductId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return product; 
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList();
        mysql.getConnection();
        
        mysql.initPS(SQL_SELECT_ALL);
  
        PreparedStatement ps = mysql.getPstm();
        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantityInStock(rs.getInt("quantityInStock"));
                product.getProductCategory().setCategoryId(rs.getInt("categoryId"));
                products.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }
        return products;
    }
    
}
