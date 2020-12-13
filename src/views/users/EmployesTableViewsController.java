package views.users;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;
import services.Security;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployesTableViewsController implements Initializable {

    @FXML
    private TableView<User> tblvEmployes;

    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> matriculeCol;
    @FXML
    private TableColumn<User, String> firstNameCol;
    @FXML
    private TableColumn<User, String> departementCol;
    @FXML
    private TableColumn<User, String> roleCol;

    private Security security;
    private ObservableList<User> employesData;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        security = new Security();
        employesData = security.fetchEmployesList();
        matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
        tblvEmployes.setItems(employesData);
    }

}
