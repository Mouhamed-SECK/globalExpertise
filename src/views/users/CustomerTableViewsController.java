/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import models.Address;
import models.Customer;
import models.Employes;
import models.User;
import services.Security;
import utilities.Utilities;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CustomerTableViewsController implements Initializable {

    @FXML
    private TableView<User> tblvCustomer;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> firstNameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> actionCol;
    @FXML
    private TableColumn<User, String> cniCol;
    @FXML
    private TableColumn<User, String> telCol;

    @FXML
    private Button addCustomerBtn;
    @FXML
    private JFXTextField filterField;
    @FXML
    private TableView<Address> tblvAddres;

    @FXML
    private TableColumn<Address, String> cityCol;
    @FXML
    private TableColumn<Address, String> districtCol;
    @FXML
    private TableColumn<Address, String> locationCol;

    @FXML
    private Button passOrderBtn;
    @FXML
    private JFXTextField cityFld;
    @FXML
    private JFXTextField districtFld;
    @FXML
    private JFXTextField locationFld;
    @FXML
    private Button addAdressBtn;

    private Security security;

    private Customer customer;

    private ObservableList<User> customerData = FXCollections.observableArrayList();
    private  ObservableList<Address> addressData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> adessActionCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        security = new Security();
        customerData = security.fetchCustomerList();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cniCol.setCellValueFactory(new PropertyValueFactory<>("cni"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        
        
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
                            //isUpdate = false;
                            int employesId = tblvCustomer.getSelectionModel().getSelectedItem().getUserId();
                            for (Iterator<User> i = customerData.iterator(); i.hasNext();) {
                                User emp = i.next();
                                if (emp.getUserId() == employesId) {
                                    i.remove();
                                }
                            }
                            //security.deleteEmployes(employesId);
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                           // isUpdate = true;
                           // employes = (Employes) tblvCustomer.getSelectionModel().getSelectedItem();
                            //handleAddEmployes(event);

                        });

                        HBox managebtn = Utilities.styleFontAwesomeIcon(editIcon, deleteIcon);
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            return cell;
        };

        FilteredList<User> filteredData = new FilteredList<>(customerData, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employe -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (((Customer) employe).getCni().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (((Customer) employe).getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.

            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblvCustomer.comparatorProperty());
        actionCol.setCellFactory(cellFoctory);
        tblvCustomer.setItems(sortedData);
    }

    @FXML
    private void handleAddCustomer(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/users/AddCustomer.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(EmployesTableViewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Utilities.showPopup(loader);
    }

    @FXML
    private void handleNewOrder(MouseEvent event) {
    }

    @FXML
    private void loadCustomerAdress(MouseEvent event) {
        customer = (Customer) tblvCustomer.getSelectionModel().getSelectedItem();
        addressData = customer.getAddresses();
        
         cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        districtCol.setCellValueFactory(new PropertyValueFactory<>("district"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        
         tblvAddres.setItems(addressData);

    }

}
