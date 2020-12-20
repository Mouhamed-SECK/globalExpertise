/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.products;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.CategoryDao;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Category;
import models.Product;
import services.Products;
import utilities.Validation;

/**
 *
 * @author ASUS
 */
public class AddProductController implements Initializable {

    @FXML
    private JFXTextField nameFld;
    @FXML
    private JFXButton chooseImgBtn;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXTextField priceFld;
    @FXML
    private JFXTextField qantityFld;
    @FXML
    private JFXComboBox<String> categoriesFld;

    private File file;
    @FXML
    private ImageView productImage;

    private CategoryDao categoryDao;

    private Products products;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        products = new Products();
        List<Category> categories = products.getCategories();

        ObservableList<String> categoryList = FXCollections.observableArrayList();

        categories.forEach(cat -> {
            categoryList.add(cat.getName());
        });

        categoriesFld.setItems(categoryList);
        categoriesFld.getSelectionModel().select(categoryList.get(0));

    }

    @FXML
    private void handleChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            productImage.setImage(image);
        }

    }

    @FXML
    private void handleSave(ActionEvent event) throws SQLException {
        String name = nameFld.getText();
        String category = categoriesFld.getValue();
        String price = priceFld.getText();
        String quantity = qantityFld.getText();

        if (Validation.isEmpty(name) && Validation.isEmpty(category) && Validation.isEmpty(price) && Validation.isEmpty(quantity) && Validation.isEmpty(file.toURI().toString())) {
            Product product = products.registerProduct(name, file, Double.parseDouble(price), Integer.parseInt(quantity), category);
            ProductTableViewController.getTblvController().getProductsData().add(product);
            this.handleClose(event);

        } else {
            Validation.setLabelMessage(lblErrors, Color.TOMATO, "Ces champs ne peuvent être vide");
        }

    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
