/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class LockFileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField filePath;
    @FXML
    private Button browse;
    @FXML
    private RadioButton basic;
    @FXML
    private RadioButton premium;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeatPassword;
    @FXML
    private Label pass;
    @FXML
    private Label repeatPass;
    @FXML
    private Button back;
    @FXML
    private Button lockFile;
    @FXML
    private Hyperlink basicLink;
    public boolean openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return false;
}
    public boolean openWebPage(URL url)
    {
        try {
        return openWebpage(url.toURI());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
        return false;
    }
    @FXML
    private void handleBasicLink(ActionEvent event) throws Exception
    {
        
        URL url1 = new URL("https://www.easylocker.org/locking-a-file");
        openWebPage(url1);
        
        
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
    private void handleLockFileButton(ActionEvent event) throws Exception
    {
        if(basic.isSelected())
        {
            String path = filePath.getText();
            AccessRights.denyAccess(path);
            FileIsLockedController.msg = "FILE IS LOCKED";
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
        else if(premium.isSelected())
        {
            String p = password.getText();
            String rp = repeatPassword.getText();
            if(!p.equals(rp))
            {
                System.out.println("Passwords don't match");
                return;
            }
            String key = Utilities.hashCode(p);
            key = Utilities.encryptPassword(key);
            //String key = password.getText();
            String path = filePath.getText();
            String actualPath = "";
            String fName = "";
            for(int i=path.length()-1;i>=0;i--)
            {
                if(path.charAt(i)=='\\')
                {
                    actualPath = path.substring(0, i);
                    fName = path.substring(i+1);
                    break;
                }
            }
            LockFile.lock(key, fName, actualPath);
            int index =0;
            for(index=fName.length()-1;index>=0;index--)
            {
                if(fName.charAt(index)=='.')
                break; 
            
            }
            String fileName = fName.substring(0, index);
            LockFile.renameDirectory(actualPath+"\\"+fileName, path);
            FileRecovery.enableRecovery(path, EasyLocker.currentUser.email);
            //AccessRights.denyAccess(path);
            //ZipUtils.zip(path, fName);
            FileIsLockedController.msg = "FILE IS LOCKED";
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
        else
        {
            System.out.println("Basic and premium both are unselected");
            return;
        }
        
    }
    @FXML
    private void handleBrowseButton(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Easy Locker - Pick A File");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        filePath.setText(selectedFile.getPath());
    }
    @FXML
    private void handleBasicRadioButton(ActionEvent event)
    {
       
        
        if(basic.isSelected())
        {
            pass.setVisible(false);
            repeatPass.setVisible(false);
            password.setVisible(false);
            repeatPassword.setVisible(false);
        }
    }
    @FXML
    private void handlePremiumRadioButton(ActionEvent event)
    {
        if(premium.isSelected())
        {
            pass.setVisible(true);
            repeatPass.setVisible(true);
            password.setVisible(true);
            repeatPassword.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        basic.setToggleGroup(group);
        premium.setToggleGroup(group);
    }    
    
}
