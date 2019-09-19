/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import static easylocker.UnlockFileController.eMail;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    static boolean flag = false;
    static String context;
    static boolean status;
    static String id;
    @FXML
    private Label incorrectOTP;
    @FXML
    private Label changePassword;
    @FXML
    private Label email;
    @FXML
    private Label repeatPassword;
    @FXML
    private TextField username;
    @FXML
    private TextField RPassword;
    @FXML
    private Label watchOut;
    @FXML
    private Label failure;
    @FXML
    private Button back;
    @FXML
    private Button forward;
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException
    {
            Parent root;
            if(context.equals("MainScreen"))
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            else
                root = FXMLLoader.load(getClass().getResource("LoginScreen1.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void handleForwardButton(ActionEvent event) throws MessagingException, IOException
    {
        failure.setVisible(false);
        incorrectOTP.setVisible(false);
        if(!status)
        {
            if(!Utilities.checkInternetConnection())
            {
                Parent root;
              
                root = FXMLLoader.load(getClass().getResource("NoInternet.fxml"));
            
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,350,300));
            stage.show();
            return;
            }
            id = username.getText();
            String dir = System.getProperty("user.dir");
            File f1 = new File(dir+"\\metadata\\users\\"+id+".txt");
            File f2 = new File(dir+"\\metadata\\userData\\"+id+".txt");
            if(!f1.exists())
            {
                failure.setVisible(true);
                return;
            }
            if(!f2.exists())
            {
                failure.setVisible(true);
                return;
            }
            status = true;
            forward.setText("ENTER");
            back.setVisible(false);
            email.setText("Enter OTP:");
            //repeatPassword.setVisible(true);
            //RPassword.setVisible(true);
            //watchOut.setVisible(true);
            String otp = Utilities.generateOTP();
            Mail.send("OTP for password reset at EasyLocker", "Your One Time Password is\n"+otp+"\n\n\n\n\n\n\n\nRegards,\nUtkarsh Deep", id);
            Mail.OTP = otp;
            username.setText("");
            
            
        }
        else
        {
            if(forward.getText().equals("ENTER"))
            {
                if(!Mail.OTP.equals(username.getText()))
                {
                    incorrectOTP.setVisible(true);
                    return;
                }
                forward.setText("Change Password");
                email.setText("Password");
                repeatPassword.setVisible(true);
                RPassword.setVisible(true);
                watchOut.setVisible(true);
                username.setText("");
            }
            else
            {
                if(!username.getText().equals(RPassword.getText()))
                {
                    failure.setText("Passwords didn't match! Try again!");
                    failure.setVisible(true);
                    return;
                }
                Utilities.changePassword(id,username.getText());
                Parent root;
            if(context.equals("MainScreen"))
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            else
                root = FXMLLoader.load(getClass().getResource("LoginScreen1.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
                    
            }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        failure.setVisible(false);
        RPassword.setVisible(false);
        repeatPassword.setVisible(false);
        watchOut.setVisible(false);
        status = false;
        incorrectOTP.setVisible(false);
        if(flag)
        {
            username.setText(EasyLocker.currentUser.email);
        }
    }    
    
}
