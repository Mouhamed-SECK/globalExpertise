/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CategoryDao;
import dao.ProductDao;
import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Category;
import models.Product;
import utilities.Utilities;

/**
 *
 * @author ASUS
 */
public class Products {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public Products() {
        categoryDao = new CategoryDao();
        productDao = new ProductDao();
    }

    public void addCategory(Category category) {
        categoryDao.add(category);
    }

    public List<Category> getCategories() {
        return categoryDao.selectAll();
    }

    public Product registerProduct(String name, File file, double price, int quantity, String categoryName) throws SQLException {
        Category categorie = categoryDao.getOne(categoryName);
        Product product = new Product(name, price, quantity, file);
        product.setProductCategory(categorie);
        product.setCategoryName(categoryName);
        product.setImage(Utilities.setImage(file));
        return productDao.add(product);
    }

    public ObservableList<Product> getAllProduct() {
        ObservableList<Product> products = productDao.selectAll();



        return products;
    }
}
