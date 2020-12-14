package views.users;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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

    private static EmployesTableViewsController tblvController;

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

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            int employesId = tblvEmployes.getSelectionModel().getSelectedItem().getUserId();
                            for (Iterator<User> i = employesData.iterator(); i.hasNext();) {
                                User emp = i.next();
                                if (emp.getUserId() == employesId) {
                                    i.remove();
                                } 
                            }
                             security.deleteEmployes(employesId);
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            return cell;

        };
        actionCol.setCellFactory(cellFoctory);
        tblvEmployes.setItems(employesData);

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
