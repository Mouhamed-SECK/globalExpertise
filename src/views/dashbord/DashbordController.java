package views.dashbord;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import models.Employes;
import models.User;
import utilities.Utilities;
import views.authentication.LoginController;

public class DashbordController implements Initializable {

    @FXML
    private Button btnSignout;

    private Pane pnlUsers;

    private Pane pnlOrders;
    private Pane pnlProducts;
    private Pane pnlComptable;

    @FXML
    private Button btnOrders;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnComptable;
    @FXML
    private Button btnOverview;

    private Utilities utils;
    @FXML
    private Text fullNameLabel;
    @FXML
    private Text roleLabel;

    private Employes loggedUser;

    @FXML
    private Text departmentLabel;
    @FXML
    private Button btnUsers1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        utils = new Utilities();
        loggedUser = LoginController.getConnexion().getLoggedUser();

        fullNameLabel.setText(loggedUser.getFirstname() + " " + loggedUser.getName().toUpperCase());
        departmentLabel.setText("Departement : " + loggedUser.getRole());
        roleLabel.setText("Role : " + loggedUser.getRole());

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnUsers) {
            pnlUsers.toFront();
        }
        if (actionEvent.getSource() == btnProducts) {
            pnlProducts.setStyle("-fx-background-color : #53639F");
            pnlProducts.toFront();
        }
        if (actionEvent.getSource() == btnComptable) {
            pnlComptable.setStyle("-fx-background-color : #02030A");
            pnlComptable.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnSignout) {
            utils.changeView(actionEvent, "/authentication/Login");
        }
    }

}
