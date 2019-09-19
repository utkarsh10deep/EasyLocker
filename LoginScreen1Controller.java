/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */


public class LoginScreen1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label confirmationError;
    @FXML
    private Label confirmationLabel;
    @FXML
    private Button confirmationYes;
    @FXML
    private Button confirmationNo;
    @FXML
    private Button help;
    @FXML
    private Button deleteId;
    @FXML
    private Button lockFile;
    @FXML
    private Button unlockFile;
    @FXML
    private Button lockFolder;
    @FXML
    private Button unlockFolder;
    @FXML
    private Label greeting;
    @FXML
    private Button logout;
    @FXML
    private Button changePassword;
    
    @FXML
    private void handleChangePasswordButton(ActionEvent event) throws IOException
    {
        ForgotPasswordController.flag = true;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleYesButton(ActionEvent event) throws IOException
    {
        String userName = EasyLocker.currentUser.email;
        String dir = System.getProperty("user.dir");
        File f1 = new File(dir+"\\metadata\\users\\"+userName+".txt");
        File f2 = new File(dir+"\\metadata\\userData\\"+userName+".txt");
        if(!f1.delete())
        {
            confirmationError.setVisible(true);
            return;
        }
        if(!f2.delete())
        {
            confirmationError.setVisible(true);
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
        
        
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleNoButton(ActionEvent event)
    {
        help.setVisible(true);
        deleteId.setVisible(true);
        lockFile.setVisible(true);
        unlockFile.setVisible(true);
        lockFolder.setVisible(true);
        unlockFolder.setVisible(true);
        //greeting.setVisible(false);
        logout.setVisible(true);
        changePassword.setVisible(true);
        
        confirmationLabel.setVisible(false);
        confirmationYes.setVisible(false);
        confirmationNo.setVisible(false);
        confirmationError.setVisible(false);
    }
    @FXML
    private void handleDeleteIdButton(ActionEvent event) throws IOException
    {
        help.setVisible(false);
        deleteId.setVisible(false);
        lockFile.setVisible(false);
        unlockFile.setVisible(false);
        lockFolder.setVisible(false);
        unlockFolder.setVisible(false);
        //greeting.setVisible(false);
        logout.setVisible(false);
        changePassword.setVisible(false);
        
        
        confirmationLabel.setVisible(true);
        confirmationYes.setVisible(true);
        confirmationNo.setVisible(true);
        
        /*
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        //stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root));
        stage.show();
        //((Node)(event.getSource())).getScene().getWindow().hide();
        */
    }
    @FXML
    private void handleLockFileButton(ActionEvent event) throws IOException
    {
        //System.out.println("Hello.........Hello");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("LockFile.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleUnlockFileButton(ActionEvent event) throws Exception
    {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("UnlockFile.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleLockFolderButton(ActionEvent event) throws IOException
    {
        ForgotPasswordController.flag = true;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("LockFolder.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleUnlockFolderButton(ActionEvent event) throws IOException
    {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("UnlockFolder.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleLogoutButton(ActionEvent event) throws Exception
    {
        EasyLocker.currentUser=null;
        
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
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
    private void handleHelpButton(ActionEvent event) throws MalformedURLException
    {
        URL url1 = new URL("https://www.easylocker.org/help");
        openWebPage(url1);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        greeting.setText(EasyLocker.currentUser.name);
        confirmationLabel.setVisible(false);
        confirmationYes.setVisible(false);
        confirmationNo.setVisible(false);
        confirmationError.setVisible(false);
        ForgotPasswordController.context = "LoginScreen";
    }    
    
}
