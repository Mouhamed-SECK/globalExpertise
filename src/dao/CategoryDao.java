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
import models.Category;


/**
 *
 * @author ASUS
 */
public class CategoryDao implements IDao<Category>{
   
    private final String SQL_SELECT_ALL ="SELECT * FROM `categorie`";
    private final String SQL_INSERT ="INSERT INTO `categorie` (`categoryId`, `name`) VALUES (?,?)";
    private final Mysql mysql;
    
    public CategoryDao() {
        mysql = new Mysql();
    }

    
    
    @Override
    public Category add(Category category) {
        mysql.getConnection();
        
        mysql.initPS(SQL_INSERT);
        
        PreparedStatement psmt = mysql.getPstm();
        try {
            psmt.setString(1, category.getName());
            
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                int categoryId = rs.getInt(1);
                category.setCategoryId(categoryId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return category;
    }

    @Override
    public List<Category> selectAll() {
        List<Category> categories = new ArrayList();
        mysql.getConnection();
        
        mysql.initPS(SQL_SELECT_ALL);
  
        PreparedStatement ps = mysql.getPstm();
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }
        return categories;
    }
    
}
