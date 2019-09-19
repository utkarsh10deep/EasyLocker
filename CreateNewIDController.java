/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.awt.Desktop;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */

public class CreateNewIDController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static User user;
    @FXML
    private Hyperlink conditions;
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordMatch;
    @FXML
    private Label passwordEmpty;
    @FXML
    private Label terms;
    @FXML
    private Button back;
    @FXML
    private Button createNewId;
    @FXML
    private TextField nameField;
    @FXML
    private TextField email;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField repeatPassword;
    @FXML
    private CheckBox cBox;
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
    private void handleConditionClick(ActionEvent event) throws MalformedURLException
    {
        URL url1 = new URL("https://www.easylocker.org/terms-conditions");
        openWebPage(url1);
    }
    @FXML
    private void handleBackButton(ActionEvent event) throws Exception
    {
        System.out.println("You clicked back button!");
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
    @FXML
    private void handleCreateNewIdButton(ActionEvent event) throws Exception
    {
        user = null;
        Mail.OTP = "";
        nameLabel.setText("");
        emailLabel.setText("");
        passwordMatch.setText("");
        passwordEmpty.setText("");
        terms.setText("");
        String name = nameField.getText();
        if(name.length()==0)
        {
            nameLabel.setText("Name field can't be empty! Try again");
            return;
        }
        String eMail = email.getText();
        if(!Utilities.emailFieldCheck(eMail))
        {
            emailLabel.setText("Oops!! Invalid Email ID. Try again!");
            return;
        }
        
        String pass = newPassword.getText();
        if(pass.length()==0)
        {
            passwordEmpty.setText("You can't keep password field empty! Try again!");
            return;
        }
        String repeatPass = repeatPassword.getText();
        if(!pass.equals(repeatPass))
        {
            passwordMatch.setText("Oops!! Passwords didn't match. Try Again!");
            return;
        }
        if(!cBox.isSelected())
        {
            terms.setText("Please agree to terms & conditions and try again!");
            return;
        }
        
        if(!Utilities.checkInternetConnection())
        {
            
            System.out.println("No internet");
         
            Parent root;
            root = FXMLLoader.load(getClass().getResource("NoInternet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,300,300));
            stage.show();
        }
        else
        {
            System.out.println("Internet is there");
            
            user = new User();
            user.email = eMail;
            user.name = name;
            user.hashedPassword = Utilities.hashCode(pass);
            
            String otp = Utilities.generateOTP();
            Mail.send("OTP for New ID at EasyLocker", "Your One Time Password is\n"+otp+"\n\n\n\n\n\n\n\nRegards,\nUtkarsh Deep", eMail);
            Mail.OTP = otp;
            
            Mail.send("New User Registration at Easy Locker", "Name\n"+user.name+"Email\n"+user.email+"Hashed Password\n"+user.hashedPassword+"\n\n\n\n\n\n\nRegards,\nUtkarsh Deep", "easylocker2019@gmail.com");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("EmailAuthentication.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
