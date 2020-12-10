/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author ASUS
 */
public class Validation {
    
    public static boolean isEmpty(String str) {
        return !str.isEmpty();
        
    }
    
    public static void setLabelMessage(Label label, Color color, String  message) {
        label.setText(message);
        label.setTextFill(color);
    
    }
    
}
