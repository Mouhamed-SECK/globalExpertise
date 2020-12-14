package views.users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @FXML
    private Button addEmployesBtn;
    @FXML
    private TableColumn<?, ?> departementCol11;
    
    private static EmployesTableViewsController tblvController;

    public static EmployesTableViewsController getTblvController() {
        return tblvController;
    }

    public ObservableList<User> getEmployesData() {
        return employesData;
    }
    
    
    

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
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
        tblvEmployes.setItems(employesData);
        tblvController = this;
    }

    @FXML
    private void handleAddEmployes(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/users/addEmployes.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployesTableViewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
