/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.Mysql;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import models.Category;


/**
 *
 * @author ASUS
 */
public class CategoryDao implements IDao<Category>{
   
    
    private final String SQL_SELECT_ALL ="SELECT * FROM `categorie`";
    private final String SQL_INSERT ="INSERT INTO `categorie` (`categoryId`, `name`) VALUES (?,?)";
    private final String SELECT_CATEGORY_BY_NAME = "SELECT `name` FROM `categorie` WHERE name=? ";
    
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
        return null;
    }
    
    public Category selectCategoryByName(String name) {
        mysql.getConnection();
        mysql.initPS(SELECT_CATEGORY_BY_NAME);
        PreparedStatement psmt = mysql.getPstm();
        Category category = null;
        try {

            psmt.setString(1, name);
            ResultSet rs = mysql.executeSelect();

            if (rs.next()) {
                category = new Category();
             
                category.setName(rs.getString("name"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return category;
    }
    
    
    
}
