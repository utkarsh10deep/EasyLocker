/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class ConfirmationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label msg;
    @FXML
    private void handleYesButton(ActionEvent event) throws IOException
    {
        msg.setText("");
        String userName = EasyLocker.currentUser.email;
        String dir = System.getProperty("user.dir");
        File f1 = new File(dir+"\\metadata\\users\\"+userName+".txt");
        File f2 = new File(dir+"\\metadata\\userData\\"+userName+".txt");
        if(!f1.delete())
        {
            msg.setText("Something went wrong! Try again!");
            return;
        }
        if(!f2.delete())
        {
            msg.setText("Something went wrong! Try again");
            return;
        }
        EasyLocker.currentUser = null;
        ((Node)(event.getSource())).getScene().getWindow().hide();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        
        
    }
    @FXML
    private void handleNoButton(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
