package views.dashbord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Employes;
import utilities.Utilities;
import views.authentication.LoginController;

public class DashbordController implements Initializable {

    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOrders;
    private Button btnUsers;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnComptable;
    @FXML
    private Text fullNameLabel;
    @FXML
    private Text roleLabel;
    @FXML
    private Button listEmployes;
    @FXML
    private Button listCustomer;
    @FXML
    private Text departmentLabel;
    @FXML
    private VBox mainContent;

    private Utilities utils;
    private Employes loggedUser;
    @FXML
    private Button btnOverview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        utils = new Utilities();
        loggedUser = LoginController.getConnexion().getLoggedUser();

        fullNameLabel.setText(loggedUser.getFirstname() + " " + loggedUser.getName().toUpperCase());
        departmentLabel.setText("Departement : " + loggedUser.getDepartement());
        roleLabel.setText("Role : " + loggedUser.getRole());
        try {
            utils.loadViewContent(mainContent, "/users/EmployesTableViews");
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == listEmployes) {
            utils.loadViewContent(mainContent, "/users/EmployesTableViews");
        }

        if (actionEvent.getSource() == listCustomer) {
            utils.loadViewContent(mainContent, "/users/CustomerTableViews");
        }
        
        if (actionEvent.getSource() == btnProducts) {
            utils.loadViewContent(mainContent, "/products/ProductTableView");
        }

        if (actionEvent.getSource() == btnSignout) {
            utils.changeView(actionEvent, "/authentication/Login");
        }
    }

}
