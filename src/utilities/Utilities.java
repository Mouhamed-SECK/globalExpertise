/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views" + view + ".fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void loadViewContent(AnchorPane anchorContent, String viewName) throws IOException {
        AnchorPane viewLoader = FXMLLoader.load(getClass().getResource("/views" + viewName + ".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(viewLoader);
    }

}
