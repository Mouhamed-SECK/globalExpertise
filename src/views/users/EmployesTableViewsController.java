package views.users;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.Employes;
import models.User;
import services.Security;
import utilities.Utilities;

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
    //private ObservableList<User> employesData;

    @FXML
    private Button addEmployesBtn;

    private static EmployesTableViewsController tblvController;

    private boolean isUpdate;

    private Employes employes;

    //Filtered
    @FXML
    private JFXTextField filterField;

    private ObservableList<User> employesData = FXCollections.observableArrayList();

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public static EmployesTableViewsController getTblvController() {
        return tblvController;
    }
    @FXML
    private TableColumn<User, String> actionCol;

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

        tblvController = this;
        matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));

        //add cell of button edit 
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //that cell created only on non-empty rows
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            isUpdate = false;
                            int employesId = tblvEmployes.getSelectionModel().getSelectedItem().getUserId();
                            for (Iterator<User> i = employesData.iterator(); i.hasNext();) {
                                User emp = i.next();
                                if (emp.getUserId() == employesId) {
                                    i.remove();
                                }
                            }
                            security.deleteEmployes(employesId);
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            isUpdate = true;
                            employes = (Employes) tblvEmployes.getSelectionModel().getSelectedItem();
                            handleAddEmployes(event);

                        });

                        HBox managebtn = Utilities.styleFontAwesomeIcon(editIcon, deleteIcon);
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            return cell;
        };

        FilteredList<User> filteredData = new FilteredList<>(employesData, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employe -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (employe.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (((Employes) employe).getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.

            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblvEmployes.comparatorProperty());
        actionCol.setCellFactory(cellFoctory);
        tblvEmployes.setItems(sortedData);

    }

    @FXML

    private void handleAddEmployes(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/users/addEmployes.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(EmployesTableViewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (isUpdate) {
            AddEmployesController addEmployesController = loader.getController();
            addEmployesController.setTextFeilds(employes.getName(), employes.getFirstname(), employes.getEmail(), employes.getRole(), employes.getDepartement());
        }
        Utilities.showPopup(loader);

    }

}
