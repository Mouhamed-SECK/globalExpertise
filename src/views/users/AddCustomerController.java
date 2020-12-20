/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.users;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import services.Security;
import utilities.Validation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddCustomerController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private JFXTextField nameFld;
    @FXML
    private JFXTextField firstNameFld;
    @FXML
    private JFXTextField emailFld;
    @FXML
    private JFXTextField numTelFld;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXTextField cniFld;
    @FXML
    private JFXTextField cityFld;
    @FXML
    private JFXTextField districtFld;
    @FXML
    private JFXTextField locationFld;
    
    private Security security;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           security = new Security();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameFld.getText();
        String firstName = firstNameFld.getText();
        String email = emailFld.getText();
        String cni = cniFld.getText();
        String numberTel = numTelFld.getText();
        
        String city = cityFld.getText();
        String location = districtFld.getText();
        String district = locationFld.getText();

        if (Validation.isEmpty(name) && Validation.isEmpty(firstName) && Validation.isEmpty(email) && Validation.isEmpty(cni) && Validation.isEmpty(numberTel) && Validation.isEmpty(city) && Validation.isEmpty(location) && Validation.isEmpty(district))  {
            User employes = security.registerClient(name, firstName, email, cni, numberTel, city, location, district);
            System.out.println(employes.toString());
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
