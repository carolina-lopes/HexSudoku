/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoihc;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class FXMLLevelsController implements Initializable {

    @FXML
    private Label lb_level1;
    @FXML
    private Label lb_level2;
    @FXML
    private Label lb_level3;
    @FXML
    private Button bt_Level1;
    @FXML
    private Button bt_Level2;
    @FXML
    private Button bt_Level3;
    @FXML
    private Button bt_voltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb_level1.setText(String.valueOf(Utilizador.getLevel1()));
        lb_level2.setText(String.valueOf(Utilizador.getLevel2()));
        lb_level3.setText(String.valueOf(Utilizador.getLevel3()));
        if(Utilizador.getLevel1()==0){
            bt_Level2.setDisable(true);
            bt_Level3.setDisable(true);
        }
        if(Utilizador.getLevel2()==0)
            bt_Level3.setDisable(true);
    }    

    @FXML
    private void onLevel1(ActionEvent event) throws IOException {
        Utilizador.setChoselvl(1);
        Parent Main_page = FXMLLoader.load(getClass().getResource("FXMLMainPage.fxml"));
        Scene Main_page_scene = new Scene(Main_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Main_page_scene);
        app_stage.show();
    }

    @FXML
    private void onLevel2(ActionEvent event) throws IOException {
        Utilizador.setChoselvl(2);
        Parent Main_page = FXMLLoader.load(getClass().getResource("FXMLMainPage.fxml"));
        Scene Main_page_scene = new Scene(Main_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Main_page_scene);
        app_stage.show();
    }

    @FXML
    private void onLevel3(ActionEvent event) throws IOException {
        Utilizador.setChoselvl(3);
        Parent Main_page = FXMLLoader.load(getClass().getResource("FXMLMainPage.fxml"));
        Scene Main_page_scene = new Scene(Main_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Main_page_scene);
        app_stage.show();
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Parent Main_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene Main_page_scene = new Scene(Main_page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(Main_page_scene);
        app_stage.show();
    }
    
}
