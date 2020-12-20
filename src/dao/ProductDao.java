/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Category;

import models.Product;
import utilities.Utilities;

/**
 *
 * @author abdel
 */
public class ProductDao implements IDao<Product> {

    private final String SQL_SELECT_ALL = "SELECT * FROM `product`";
    private final String SQL_INSERT = "INSERT INTO `product` (`code`,`name`, `price`,`quantityInStock`, `productImg`,`categoryId`) VALUES (?,?,?,?,?,?)";
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

            File productImg = product.getProductImg();
            FileInputStream fis = new FileInputStream(productImg);
            psmt.setBinaryStream(5, (InputStream) fis, (int) productImg.length());

            psmt.setInt(6, product.getProductCategory().getCategoryId());

            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                product.setProductId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }

        return product;
    }

    @Override
    public ObservableList<Product> selectAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();

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
                CategoryDao categoryDao = new CategoryDao();
                Category productCategory = categoryDao.getOne(rs.getInt("categoryId"));

                product.setProductCategory(productCategory);
                product.setCategoryName(productCategory.getName());

                File file = new File(product.getName() + ".png");

                FileOutputStream fos = new FileOutputStream(file);
                byte b[];
                Blob blob;
                blob = rs.getBlob("productImg");
                b = blob.getBytes(1, (int) blob.length());
                fos.write(b);

                product.setProductImg(file);

               
                product.setImage(Utilities.setImage(file));

                products.add(product);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection();
        }
        return products;
    }

}
