/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author utkarsh.deep
 */
public class FXMLDocumentController implements Initializable {
    static boolean remember;
    @FXML
    private Button clear;
    @FXML
    private CheckBox rememberMe;
    @FXML 
    private Button login;
    @FXML
    private Button createNewID;
    @FXML 
    private Button forgotPassword;
    @FXML
    private Label wrongCredentials;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private void handleClearButton(ActionEvent event)
    {
        remember = false;
        username.setText("");
        password.setText("");
        
    }
    @FXML
    private void handleLoginButton(ActionEvent event) throws Exception {
        //System.out.println("You clicked login button!");
        //label.setText("Hello World!");
        wrongCredentials.setText("");
        String userName =username.getText();
        String pass = password.getText();
        pass = Utilities.hashCode(pass);
        boolean loginCheck = Utilities.loginCheck(userName, pass);
        if(!remember)
            if(!loginCheck)
            {
                wrongCredentials.setText("Wrong Credentials! Try Again!");
                return;
            }
        String name = Utilities.fetchName(userName);
        EasyLocker.loadUser(name, pass, userName);
        System.out.println("Password and Username validated!");
        String curDir = System.getProperty("user.dir");
        if(rememberMe.isSelected())
        {
            File f = new File(curDir+"\\metadata\\savedData.txt");
            if(f.exists())
            {
               f.delete();
            }
            
            f.createNewFile();
            
            FileWriter fw = new FileWriter(f);
            String user = EasyLocker.currentUser.email;
            //String nam = EasyLocker.currentUser.name;
            fw.write(user);
            fw.close();
            
        
        
        }
        else
        {
            File f = new File(curDir+"\\metadata\\savedData.txt");
            if(f.exists())
            {
                f.delete();
            }
            
                    
        }
        
        
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
    private void handleForgotPasswordButton(ActionEvent event) throws IOException
    {
        ForgotPasswordController.flag = false;
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
    private void handleCreateNewIDButton(ActionEvent event) throws Exception
    {
        System.out.println("You clicked create New ID button!");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("CreateNewID.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Easy Locker");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root,550,600));
        stage.show();
        // Hide this current window (if this is what you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ForgotPasswordController.context = "MainScreen";
        EmailAuthenticationController.purpose="";
        String curDir = System.getProperty("user.dir");
        File f = new File(curDir+"\\metadata\\savedData.txt");
        if(f.exists())
        {
            rememberMe.setSelected(true);
           remember = true;
           BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(f));
                String email = br.readLine();
                username.setText(email);
                password.setText("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                br.close();
                //String name = br.readLine();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
           
           
        }
        else
        {
            rememberMe.setSelected(false);
            remember = false;
        }
        
        
        
        
    }    
    
}
