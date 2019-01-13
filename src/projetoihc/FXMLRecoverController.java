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
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class FXMLRecoverController implements Initializable {

    @FXML
    private TextField lb_UserName;
    @FXML
    private Label lb_Pass;
    @FXML
    private Button bt_Check;
    @FXML
    private Button bt_Check1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRecover(ActionEvent event) {
        Connection c=null;
        Statement stmt = null;
        if(lb_UserName.getText()==""){
            lb_Pass.setText("Please insert UserName");
        }
        try{
            c=DriverManager.getConnection("jdbc:sqlite:SudokuDB.db");
            c.setAutoCommit(false);
            
            System.out.println("Openned Database Sucessfully");
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Utilizador WHERE Nome ="+ "'" + lb_UserName.getText()+"'");

            while(rs.next()){
                if(rs.getString("Nome")!=null){
                    String username = rs.getString("Nome");
                    System.out.println("Nome = "+username);
                    String password = rs.getString("Password");
                    System.out.println("Password="+password);
                        lb_Pass.setText("Your password is: "+password);
                        
                } 
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception ex){
            System.out.println(ex.getClass().getName()+": "+ex.getMessage());
            System.exit(0);
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
    
}
