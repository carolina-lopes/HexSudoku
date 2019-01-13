/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoihc;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class FXMLSingInController implements Initializable {

    @FXML
    private Button bt_registar;
    @FXML
    private Button bt_voltar;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ProjetoIHC.stage.setResizable(false);
    }    

    @FXML
    private void onResgistry(ActionEvent event) throws SQLException, IOException {
        if (saveCredentials()) {
            Parent menu_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene menu_page_scene = new Scene(menu_page);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(menu_page_scene);
            app_stage.show();
        } else {
            tf_username.clear();
            tf_password.clear();
        }
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
       Parent main = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene main_page_scene = new Scene(main);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page_scene);
        app_stage.show();
    }
    
    
     private boolean saveCredentials() throws SQLException {
        boolean let_in = false;

        //System.out.println("SELECT * FROM User WHERE username="+"'"+tf_UserReg.getText()+"'"+"AND password="+tf_PassReg.getText()+"'"+"AND email="+tf_EmailReg.getText()+"'");
        Connection c = null;
        Statement stmt = null;
        try {

            c = DriverManager.getConnection("jdbc:sqlite:SudokuDB.db");
            c.setAutoCommit(false);

            System.out.println("Openned Database Sucessfully");
            stmt = c.createStatement();
            if(tf_username.getText().isEmpty()){
                let_in=false;
                
            }else{
                System.out.println(tf_username.getText());
                stmt.executeUpdate("INSERT INTO Utilizador " + "VALUES ('" + tf_username.getText() + "', '" + tf_password.getText() + "', '" + '0'+"', '" + '0' +"', '"+'0'+ "')");
                c.commit();
                let_in = true;
            }
        
        } catch (Exception ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        
        stmt.close();
        c.close();
        System.out.println(let_in);
        if(let_in==true)
        System.out.println("Operação bem sucedida.");
        return let_in;
    }
    
}
