/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.products;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import models.Category;
import models.Employes;
import models.Product;
import models.User;
import services.Products;
import utilities.Utilities;
import utilities.Validation;
import views.users.EmployesTableViewsController;

/**
 *
 * @author ASUS
 */
public class ProductTableViewController implements Initializable {

    private static ProductTableViewController tblvController;

    @FXML
    private TableColumn<Product, String> codeCol;
    @FXML
    private TableColumn<Product, ImageView> photoCol;
    @FXML
    private TableColumn<Product, String> libelleCol;
    @FXML
    private TableColumn<Product, String> priceCol;
    @FXML
    private TableColumn<Product, String> qteCol;
    @FXML
    private TableColumn<Product, String> categoryCol;
    @FXML
    private TableColumn<Product, String> actionCol;
    @FXML
    private Button addProductBtn;
    @FXML
    private JFXTextField filterField;
    @FXML
    private JFXTextField newCategoryFld;
    @FXML
    private Button addCategory;
    @FXML
    private Label errorlbl;

    private Products products;

    private ObservableList<Product> productsData = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> tblvProduct;



    public static ProductTableViewController getTblvController() {
        return tblvController;
    }

    public ObservableList<Product> getProductsData() {
        return productsData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        products = new Products();
        productsData = products.getAllProduct();
        tblvController = this;

        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        photoCol.setCellValueFactory(new PropertyValueFactory<>("image"));

        FilteredList<Product> filteredData = new FilteredList<>(productsData, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.

            });
        });


        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblvProduct.comparatorProperty());
        tblvProduct.setItems(sortedData);

    }

    @FXML
    private void handleAddEmployes(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/products/addProduct.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(EmployesTableViewsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Utilities.showPopup(loader);
    }

    @FXML
    private void handleAddCategory(ActionEvent event) {
        String categoryName = newCategoryFld.getText();

        if (Validation.isEmpty(categoryName)) {
            Category category = new Category(categoryName);
            products.addCategory(category);
            newCategoryFld.clear();
            errorlbl.setText("");
            Utilities.showAlert("Nouvelle Catégorie", "Catégorie : " + categoryName + " Enregistrer avec succés");

        } else {
            Validation.setLabelMessage(errorlbl, Color.TOMATO, "Ces champs ne peuvent être vide");
        }
    }

}
