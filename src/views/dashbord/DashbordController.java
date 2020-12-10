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
import models.Employes;
import models.User;
import utilities.Utilities;
import views.authentication.LoginController;

public class DashbordController implements Initializable {

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlUsers;

    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlProducts;
    @FXML
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
    private Label fullNameLabel;
    @FXML
    private Label roleLabel;

    private Employes loggedUser;

    private LoginController loginController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        utils = new Utilities();
        loginController = new LoginController();
      /*  loggedUser = loginController.getLoggedUser();
        System.out.println(loggedUser.getEmail());
        System.out.println(loggedUser.getRole());

       // fullNameLabel.setText(loggedUser.getEmail());
        roleLabel.setText(loggedUser.getRole());*/

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
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        utils.changeView(event, "authentication/Login");
    }
}
