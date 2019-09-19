/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easylocker;

import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author utkarsh.deep
 */
public class EasyLocker extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Easy Locker");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root, 550, 600);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static User currentUser;
    public static void loadUser(String name,String password,String username)
    {
        currentUser = new User();
        currentUser.email = username;
        currentUser.name = name;
        currentUser.hashedPassword = password;
    }
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        launch(args);
    }
    
}
