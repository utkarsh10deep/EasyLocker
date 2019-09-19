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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author utkarsh.deep
 */
public class UnlockFileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String eMail="";
    public static String fName="";
    public static String fPath="";
    @FXML
    private Button forgotPassword;
    @FXML
    private Label internetLabel;
    @FXML
    private Label passwordIncorrect;
    @FXML
    private TextField fileName;
    @FXML
    private Label fileNameLabel;
    @FXML
    private Hyperlink fileNameLink;
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
    private Label pass;
    @FXML
    private Button back;
    @FXML
    private Button unlockFile;
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
    private void handleLink(ActionEvent event) throws Exception
    {
        
        URL url1 = new URL("https://www.easylocker.org/unlocking-a-file");
        openWebPage(url1);
        
        
    }
    
    @FXML
    private void handleForgotPasswordButton(ActionEvent event) throws IOException, MessagingException 
    {
        boolean internet = Utilities.checkInternetConnection();
        if(!internet)
        {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("NoInternet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,350,400));
            stage.show();
            }
            fName = fileName.getText();
            fPath = filePath.getText();
            String otp = Utilities.generateOTP();
            eMail = FileRecovery.fetchRecoderyId(filePath.getText()+"\\"+fileName.getText());
            Mail.send("OTP for File "+fileName.getText()+" at EasyLocker", "Your One Time Password is\n"+otp+"\n\n\n\n\n\n\n\nRegards,\nUtkarsh Deep",eMail );
            Mail.OTP = otp;
            
            EmailAuthenticationController.purpose = "Unlock File";
            Parent root;
            root = FXMLLoader.load(getClass().getResource("EmailAuthentication.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Easy Locker");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(new Scene(root,550,600));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
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
    private void handleUnlockFileButton(ActionEvent event) throws Exception
    {
        passwordIncorrect.setText("");
        if(basic.isSelected())
        {
            String path = filePath.getText();
            if(fileName.getText().length()>0)
                path = path+"\\"+fileName.getText();
            AccessRights.grantAccess(path);
            FileIsLockedController.msg = "FILE IS UNLOCKED";
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
            	//System.out.println("Enter the filename you want to unlock:");
                String fName = "";
		//String fName = sc.nextLine();
		//System.out.println("Enter path:");
		//String path = sc.nextLine();
                String path = "";
		//System.out.println("Enter the password");
		//String password = sc.nextLine();
                String lockPassword = Utilities.hashCode(password.getText());
                lockPassword = Utilities.encryptPassword(lockPassword);
                String fPath = filePath.getText();
                fPath = fPath+"\\"+fileName.getText();
                
                //fPath is FULL_PATH
                //String recoveryID = FileRecovery.fetchRecoderyId(fPath);
                //FileRecovery.disableRecovery(fPath);
                //AccessRights.grantAccess(fPath);
                for(int i=fPath.length()-1;i>=0;i--)
                {
                    if(fPath.charAt(i)=='\\')
                    {
                        path = fPath.substring(0, i);
                        fName = fPath.substring(i+1);
                        break;
                    }
                }
                
		int index =0;
                for(index=fName.length()-1;index>=0;index--)
                {
                    if(fName.charAt(index)=='.')
                    break; 
            
                }
                String fileName = fName.substring(0, index);
		LockFile.renameDirectory(path+"\\"+fName,path+"\\"+fileName);
                //FileRecovery.disableRecovery(path+"\\"+fileName);
		System.out.println("fName: "+fName);
                System.out.println("path: "+path);
                //originally it was - (lockPassword,fName,path)
		if(PasswordValidate.validate(lockPassword,fName,path))
		{
			System.out.println("Password validated successfully");
                        
		}
		else
		{
                        
			System.out.println("Password incorrect");
			LockFile.renameDirectory(path+"\\"+fileName,path+"\\"+fName);
                        passwordIncorrect.setText("Incorrect Password! Try again!");
                        return;
			//System.exit(1);
		}
                
                FileRecovery.disableRecovery(path+"\\"+fileName);
                //AccessRights.grantAccess(fPath);
		UnlockFile.generateUnlockedFile(fName,path,path+"\\"+fileName);
		File folder = new File(path+"\\"+fileName);
		folder.delete();
            FileIsLockedController.msg = "FILE IS UNLOCKED";
            
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
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("Easy Locker - Pick A Folder");
        Stage stage = new Stage();
        File selectedFile = folderChooser.showDialog(stage);
        filePath.setText(selectedFile.getPath());
    }
    @FXML
    private void handleBasicRadioButton(ActionEvent event)
    {
       
        
        if(basic.isSelected())
        {
            fileName.setVisible(true);
            fileNameLabel.setVisible(true);
            fileNameLink.setVisible(true);
            pass.setVisible(false);
            forgotPassword.setVisible(false);
            internetLabel.setVisible(false);
            password.setVisible(false);
           
        }
    }
    @FXML
    private void handlePremiumRadioButton(ActionEvent event)
    {
        if(premium.isSelected())
        {
            fileName.setVisible(true);
            fileNameLabel.setVisible(true);
            fileNameLink.setVisible(true);
            pass.setVisible(true);
           forgotPassword.setVisible(true);
            internetLabel.setVisible(true);
            password.setVisible(true);
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileName.setText("");
        ToggleGroup group = new ToggleGroup();
        basic.setToggleGroup(group);
        premium.setToggleGroup(group);
    }     
    
}
