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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class LockFolderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField folderPath;
    @FXML
    private Button browse; 
 
    @FXML
    private Button back;
    @FXML
    private Button lockFolder;
    @FXML
    private void handleBrowseButton(ActionEvent event)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Easy Locker - Pick A Folder");
        Stage stage = new Stage();
        File selectedFile = directoryChooser.showDialog(stage);
        folderPath.setText(selectedFile.getPath());
    }
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException
    {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("LoginScreen1.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
    }
    @FXML
    private void handleLockFolderButton(ActionEvent event) throws Exception
    {
        //if(basic.isSelected())
        {
            String path = folderPath.getText();
            System.out.println(path);
            AccessRights.denyAccess(path);
            FileIsLockedController.msg = "FOLDER IS LOCKED";
            Parent root;
        root = FXMLLoader.load(getClass().getResource("ActionMessage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ToggleGroup group = new ToggleGroup();
        //basic.setToggleGroup(group);
        //premium.setToggleGroup(group);
    }    
    
}
