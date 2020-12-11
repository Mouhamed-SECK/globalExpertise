/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.authentication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import models.Employes;
import models.User;
import services.Security;
import utilities.Utilities;
import utilities.Validation;

/**
 *
 * @author Mouhamed SECK
 */
public class LoginController implements Initializable {

   @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label btnForgot;
    @FXML
    private Label lblErrors;
    @FXML
    private Button loginBtn;

    private Employes loggedUser;
    private final Security security;
    private final Utilities utils;
    
    public static LoginController connexion;
    
    public static LoginController getConnexion () {
        return connexion;
    }

    public Employes getLoggedUser() {
        return loggedUser;
    }

    public LoginController() {
        security = new Security();
        utils = new Utilities();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connexion = this;
    }
    

    @FXML
    private void handleLoginUser(ActionEvent event) {
        String login = txtUsername.getText();
        String password = txtPassword.getText();

        if (Validation.isEmpty(login) && Validation.isEmpty(password)) {
           loggedUser = security.logInUser(login, password);
            if (loggedUser != null) {
                Validation.setLabelMessage(lblErrors, Color.GREEN, "Authentification avec success..Redirection...");
                utils.changeView(event, "/dashbord/Dashbord");

            } else {
                Validation.setLabelMessage(lblErrors, Color.TOMATO, "Login ou mot de passe incorect");
            }

        } else {
            Validation.setLabelMessage(lblErrors, Color.TOMATO, "Ces champs ne peuvent être vide");
        }
    }

}
