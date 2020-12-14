package views.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import models.User;
import services.Security;
import utilities.Validation;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddEmployesController implements Initializable {

    @FXML
    private JFXTextField nameFld;
    @FXML
    private JFXTextField firstNameFld;
    @FXML
    private JFXTextField emailFld;
    @FXML
    private JFXComboBox<String> departFld;
    @FXML
    private JFXComboBox<String> roleFld;
    @FXML
    private ImageView avatar;
    @FXML
    private JFXButton chooseImgBtn;

    private File file;
    @FXML
    private Label lblErrors;
    
    private  Security security;
    @FXML
    private Label avatarNameLbl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       security = new Security();
        // TODO
        departFld.getItems().addAll("Commande", "Livraison", "Comptabilité");
        departFld.getSelectionModel().select("Commande");

        departFld.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
                if ("Commande".equals(newvalue)) {
                    roleFld.getItems().clear();
                    roleFld.getItems().addAll("Commercial", "Responsable SC");
                    roleFld.getSelectionModel().select("Commercial");
                } else if ("Livraison".equals(newvalue)) {
                    roleFld.getItems().clear();
                    roleFld.getItems().addAll("Preparateur", "Responsable SL");
                    roleFld.getSelectionModel().select("Preparateur");
                } else {
                    roleFld.getItems().clear();
                    roleFld.getItems().addAll("Comptable", "Responsable Scompt");
                    roleFld.getSelectionModel().select("Comptable");
                }

            }
        });
    }

    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameFld.getText();
        String firstName = firstNameFld.getText();
        String email = emailFld.getText();
        String departement = departFld.getValue();
        String role = roleFld.getValue();

        if (Validation.isEmpty(name) && Validation.isEmpty(firstName) && Validation.isEmpty(email)  && Validation.isEmpty(departement)  && Validation.isEmpty(role) &&  Validation.isEmpty(file.toURI().toString())) {
            User employes = security.registerEmployes(name, firstName, email, departement, role, file);
            EmployesTableViewsController.getTblvController().getEmployesData().add(employes); 
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

    @FXML
    private void handleChooseFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        /* FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);*/
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            avatar.setImage(image);
        }

    }

}
