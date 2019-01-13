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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class FXMLDocumentController implements Initializable {

     private Label label;
    @FXML
    private TextField tf_nomeUtilizador;
    @FXML
    private TextField tf_password;
    @FXML
    private Button bt_login;
    
    public static Stage stage;
    @FXML
    private Label lb_signIn;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ProjetoIHC.stage.setResizable(false);
    }    

  
    @FXML
    private void onSignIn(MouseEvent event) throws IOException {
        Parent SignUp_page = FXMLLoader.load(getClass().getResource("FXMLSingIn.fxml"));
        Scene signup_page_scene = new Scene(SignUp_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_page_scene);
        app_stage.show();
    }
    
     @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        if(isValidCredentials()){
        Parent Main_page = FXMLLoader.load(getClass().getResource("FXMLLevels.fxml"));
        Scene Main_page_scene = new Scene(Main_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Main_page_scene);
        app_stage.show();
        }
       
    }

    @FXML
    private void onHelp(MouseEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("FXMLHelp.fxml"));
        Scene main_page_scene = new Scene(main);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page_scene);
        app_stage.show();
    }
    
     private boolean isValidCredentials() throws SQLException{
        boolean let_in=false;
        
        System.out.println("SELECT * FROM Utilizador WHERE Nome="+"'"+tf_nomeUtilizador.getText()+"'"+"AND Password='"+tf_password.getText()+"'");
        
        Connection c=null;
        Statement stmt = null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:SudokuDB.db");
            c.setAutoCommit(false);
            
            System.out.println("Openned Database Sucessfully");
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Utilizador WHERE Nome ="+ "'" + tf_nomeUtilizador.getText()+"'"+"AND Password ="+"'"+tf_password.getText()+"'");
        
            while(rs.next()){
                if(rs.getString("Nome")!=null&&rs.getString("Password")!=null){
                    String username = rs.getString("Nome");
                    System.out.println("Nome = "+username);
                    String password = rs.getString("Password");
                    System.out.println("Password="+password);
                    let_in=true;
                    Utilizador.setNome(username);
                    Utilizador.setPass(password);
                    Utilizador.setLevel1(Integer.valueOf(rs.getString("Level1")));
                    Utilizador.setLevel2(Integer.valueOf(rs.getString("Level2")));
                    Utilizador.setLevel3(Integer.valueOf(rs.getString("Level3")));
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
        
        System.out.println("Operação bem sucedida.");
        return let_in;
    }

    @FXML
    private void forgotPass(MouseEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("FXMLRecover.fxml"));
        Scene main_page_scene = new Scene(main);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page_scene);
        app_stage.show();
        
    }
    
}
