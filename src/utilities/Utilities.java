/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ASUS
 */
public class Utilities {

    public static String generateRandomStringAlphaNumericString(int targetStringLength) {
        int leftLimit = 48;
        int rightLimit = 122;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     *
     * @param event
     * @param view
     */
    public void changeView(ActionEvent event, String view) {
        try {

            //add you loading or delays - ;-)
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views" + view + ".fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void loadViewContent(VBox anchorContent, String viewName) throws IOException {
        AnchorPane viewLoader = FXMLLoader.load(getClass().getResource("/views" + viewName + ".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(viewLoader);
    }

    public static void showPopup(FXMLLoader loader) {
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public static HBox styleFontAwesomeIcon(FontAwesomeIconView deleteIcon, FontAwesomeIconView editIcon) {
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

        HBox managebtn = new HBox(editIcon, deleteIcon);
        managebtn.setStyle("-fx-alignment:center");
        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

        return managebtn;

    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static ImageView setImage(File file) {
        Image image = new Image(file.toURI().toString());

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        return imageView;

    }
}
