/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

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

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class EmailAuthenticationController implements Initializable {

    public static String purpose = "";
    @FXML
    private Label email;
    @FXML
    private Label incorrectOtp;
    @FXML
    private Label userExists;
    @FXML
    private Label correctOtp;
    @FXML
    private Button back;
    @FXML
    private Button createId;
    @FXML
    private TextField otp;
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
     Parent root;
            root = FXMLLoader.load(getClass().getResource("CreateNewID.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        
    }
    @FXML
    private void handleCreateIdButton(ActionEvent event) throws Exception {
        if(createId.getText().equals("Unlock File"))
        {
            back.setVisible(false);
            incorrectOtp.setText("");
            correctOtp.setText("");
            String userOtp = otp.getText();
            if(userOtp.equals(Mail.OTP))
            {
            
                
            
            
                correctOtp.setText("OTP SUCCESSFULLY VALIDATED!");
                
                //code to unlock file without knowing password
                
                String path = UnlockFileController.fPath+"\\"+UnlockFileController.fName;
                AccessRights.grantAccess(path);
                
                FileRecovery.recoverFile(path);
                
                FileIsLockedController.msg = "FILE IS UNLOCKED";
                Parent root;
                root = FXMLLoader.load(getClass().getResource("ActionMessage.fxml"));
                
                
                Stage stage = new Stage();
                stage.setTitle("Easy Locker");
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.setScene(new Scene(root,550,600));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            
            }
            else
            {
                incorrectOtp.setText("Incorrect OTP! Try again!");
            
            }
            
            return;
        }
        if(createId.getText().equals("LoginScreen"))
        {
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            return;
        }
        userExists.setText("");
        incorrectOtp.setText("");
        correctOtp.setText("");
        String userOtp = otp.getText();
        if(userOtp.equals(Mail.OTP))
        {
            
            if(!Utilities.createUser(CreateNewIDController.user.email, CreateNewIDController.user.hashedPassword, CreateNewIDController.user.name))
            {
                userExists.setText("User Already Exits! Go back and try again!");
                return;
            }
            
            Mail.send("NEW REGISTERED ID AT EASY LOCKER - DO_NOT_REPLY", "Congratulations on successfull creation of new ID on your Easy Locker desktop.\nPlease note that your id is created only on your desktop to facilitate OTP based recovery of your file in case you lose your password.\n\n\n\nEASY LOCKER PROMISE OF TRUST- Your file is 100% safe and recoverable if:\n\n1. You don't alter generated folder name of corresponding file.\n2. You don't explicitly alter any file content inside that folder.\n3. You remember your password or you have access to your registered email id with which file was locked\n\n\n\n\n\n\n\nThanks for choosing Easy Locker!\n\nRegards,\nUtkarsh Deep", CreateNewIDController.user.email);
            //Mail.send("NEW USER REGISTRATION AT EASY LOCKER","Username: \n"+CreateNewIDController.user.email+"\nName:\n"+CreateNewIDController.user.name,"easylocker2019@gmail.com");
            correctOtp.setText("OTP SUCCESSFULLY VALIDATED!");
            createId.setText("LoginScreen");
            
        }
        else
        {
            incorrectOtp.setText("Incorrect OTP! Try again!");
            
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(purpose.equals("Unlock File"))
        {
            back.setVisible(false);
            createId.setText(purpose);
            email.setText(UnlockFileController.eMail);
        }
        else
        {
            email.setText(CreateNewIDController.user.email);
        }
        // TODO
    }    
    
}
