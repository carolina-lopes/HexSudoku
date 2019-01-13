/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoihc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

/**
 * FXML Controller class
 *
 * @author carol
 */
public class FXMLMainPageController implements Initializable {

 
    @FXML
    private ImageView bt_PlayPause;
    @FXML
    private AnchorPane ap_Pause;
    
    private int flag=0;
    @FXML
    private Button bt_check;
    @FXML
    private ImageView bt_home;
    @FXML
    private Label lbl_time;
    @FXML
    private Canvas canvas;
    
    int player_selected_row;
    
    int player_selected_col;
    
    GameBoard gameboard;

    

   
    class timeThread extends Thread{
        private Label lbl_time;
        private int i=0;
        
        timeThread(Label lbl_time){
            this.lbl_time=lbl_time;
        }
        @Override
        public void run(){
            while(true){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        lbl_time.setText(String.valueOf(i));
                    }
                });
                i++;
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    timeThread timer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       bt_check.setDisable(true);
        gameboard = new GameBoard();
		//Get graphics context from canvas
       GraphicsContext context = canvas.getGraphicsContext2D();
       drawOnCanvas(context);
       timer=new timeThread(lbl_time);
       timer.setDaemon(true);
       timer.start();
       timer.suspend();
    } 
    
    @FXML
    private void onCheckPressed(ActionEvent event) throws IOException, SQLException {          
        if(gameboard.verify()){
                String url = "jdbc:sqlite:SudokuDB.db";

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if(Utilizador.getChoselvl()==1){
                if(Utilizador.getLevel1()>Integer.valueOf(lbl_time.getText()) || Utilizador.getLevel1()==0){
                        String sql = "UPDATE Utilizador SET Level1 = ? "
                       + "WHERE Nome = ?";
                   try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                      // set the corresponding param
                      System.out.println(Integer.valueOf(lbl_time.getText()));
                      pstmt.setInt(1, Integer.valueOf(lbl_time.getText()));
                      pstmt.setString(2, Utilizador.getNome());
                      pstmt.executeUpdate();
                      // update 
                      pstmt.executeUpdate();
                      pstmt.close();
                    } catch (SQLException e) {
                      System.out.println(e.getMessage());
                  }

                   Utilizador.setLevel1(Integer.valueOf(lbl_time.getText()));
                }

            }
            if(Utilizador.getChoselvl()==2){
                if(Utilizador.getLevel2()>Integer.valueOf(lbl_time.getText())|| Utilizador.getLevel2()==0){
                    String sql = "UPDATE Utilizador SET Level2 = ? "
                    + "WHERE Nome = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                       // set the corresponding param
                       System.out.println(Integer.valueOf(lbl_time.getText()));
                       pstmt.setInt(1, Integer.valueOf(lbl_time.getText()));
                       pstmt.setString(2, Utilizador.getNome());
                       pstmt.executeUpdate();
                       // update 
                       pstmt.executeUpdate();
                       pstmt.close();
                      } catch (SQLException e) {
                       System.out.println(e.getMessage());
                   }

                    Utilizador.setLevel2(Integer.valueOf(lbl_time.getText()));
                } 
        }
        
        if(Utilizador.getChoselvl()==3){
            if(Utilizador.getLevel3()>Integer.valueOf(lbl_time.getText())|| Utilizador.getLevel3()==0){
                String sql = "UPDATE Utilizador SET Level3 = ? "
                + "WHERE Nome = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                   // set the corresponding param
                   System.out.println(Integer.valueOf(lbl_time.getText()));
                   pstmt.setInt(1, Integer.valueOf(lbl_time.getText()));
                   pstmt.setString(2, Utilizador.getNome());
                   pstmt.executeUpdate();
                   // update 
                   pstmt.executeUpdate();
                   pstmt.close();
                } catch (SQLException e) {
                   System.out.println(e.getMessage());
               }
               Utilizador.setLevel3(Integer.valueOf(lbl_time.getText()));
            }
            
        }
        
        conn.close();
         
       Parent main = FXMLLoader.load(getClass().getResource("FXMLLevels.fxml"));
       Scene main_page_scene = new Scene(main);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(main_page_scene);
       app_stage.show();
        }
         
    }
        
       
        
    
 

    @FXML
    private void onPlayPause(MouseEvent event) throws IOException, InterruptedException {
        if(flag==0){
          File file = new File("src/iconfinder_pause.png");
            Image image = new Image(file.toURI().toString());
            bt_PlayPause.setImage(image);
            ap_Pause.toBack();
            bt_check.setDisable(false);
            flag=1;
            timer.resume();
        }
        else{
            File file = new File("src/iconfinder_play.png");
            Image image = new Image(file.toURI().toString());
            bt_PlayPause.setImage(image);
            ap_Pause.toFront();
            bt_check.setDisable(true);
            flag=0;
            timer.suspend();
           
            
        }
    }

    @FXML
    private void goHome(MouseEvent event) throws IOException {
        
        Parent main = FXMLLoader.load(getClass().getResource("FXMLLevels.fxml"));
        Scene main_page_scene = new Scene(main);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_page_scene);
        app_stage.show();
    }
    
    public void drawOnCanvas(GraphicsContext context) {

        context.clearRect(0, 0, 500, 500);
        // desenhar retangulos
        for(int row = 0; row<16; row++) {
                for(int col = 0; col<16; col++) {
                        // encontra y
                        int position_y = row * 28 ;
                        // encontra x
                        int position_x = col * 30 ;
                        // define comprimento quadrado
                        int width = 26;
                        if(row<4 && (col<4||col>=8 && col<12)||row>=4 && row<8 && (col>=4 && col<8|| col>=12)||row>=8 && row<12 && (col<4||col>=8 && col<12)||row>=12 && (col>=4 && col<8|| col>=12)){
                            context.setFill(javafx.scene.paint.Color.GAINSBORO);
                        }
                        else
                            context.setFill(javafx.scene.paint.Color.WHITE);
                        // deixa os retangulos com cantos arredondados
                        context.fillRoundRect(position_x, position_y, width, width, 5, 5);
                }
        }

        context.setStroke(javafx.scene.paint.Color.BLUEVIOLET);
        //margem quadrado selecionado
        context.setLineWidth(2);
        //linha a volta do quadrado arredondada
        context.strokeRoundRect(player_selected_col * 30, player_selected_row * 28 , 26, 26, 10, 10);

        char[][] initial = gameboard.getInitial();
        for(int row = 0; row<16; row++) {
                for(int col = 0; col<16; col++) {
                        int position_y = row *28+20;
                        int position_x = col * 30+15;
                        context.setFont(javafx.scene.text.Font.font(22));
                        context.setFill(javafx.scene.paint.Color.DARKSLATEGRAY);
                        context.setTextAlign(TextAlignment.CENTER);
                        context.fillText(initial[row][col] + "", position_x, position_y);
                        context.setStroke(javafx.scene.paint.Color.CHARTREUSE);
                        //margem quadrado selecionado
                        context.setLineWidth(2);
                        //linha a volta do quadrado arredondada
                        context.strokeRoundRect(player_selected_col * 30, player_selected_row * 28 , 26, 26, 10, 10);

                }
        }

        char[][] player = gameboard.getPlayer();
            for(int row = 0; row<16; row++) {
                    for(int col = 0; col<16; col++) {
                        int position_y = row * 28 +20;//soma se para ter algum offset ou seja ajustar aos quadradinhos
                        int position_x = col * 30+15 ;
                        if(player[row][col]!=initial[row][col]){
                            context.setFont(javafx.scene.text.Font.font(22));
                            context.setStroke(javafx.scene.paint.Color.RED);
                            //margem quadrado selecionado
                            context.setLineWidth(2);
                        }

                        //linha a volta do quadrado arredondada
                        context.strokeRoundRect(player_selected_col * 30, player_selected_row * 28 , 26, 26, 10, 10);
                        context.fillText(player[row][col] + "", position_x, position_y);    
                    }
            }
}
    
    @FXML
    public void canvasMouseClicked() {
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int mouse_x = (int) event.getX();
				int mouse_y = (int) event.getY();

				//converte a posição do rato em linha e coluna
				// we are going to take advantage of the way integers are treated and we are going to divide by a cell's width
				// this way any value between 0 and 449 for x and y is going to give us an integer from 0 to 8, which is exactly what we are after
				player_selected_row = (int) (mouse_y / 28);
				player_selected_col = (int) (mouse_x / 30);
				drawOnCanvas(canvas.getGraphicsContext2D());
			}
		});
	}
       

     @FXML
    private void btZeroPressed(ActionEvent event) {

        gameboard.modifyPlayer('0', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btOnePressed(ActionEvent event) {
        gameboard.modifyPlayer('1', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btTwoPressed(ActionEvent event) {
         gameboard.modifyPlayer('2', player_selected_row, player_selected_col);
         drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btThreePressed(ActionEvent event) {
        gameboard.modifyPlayer('3', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btEightPressed(ActionEvent event) {
        gameboard.modifyPlayer('8', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btAPressed(ActionEvent event) {
        gameboard.modifyPlayer('A', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btBPressed(ActionEvent event) {
        gameboard.modifyPlayer('B', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btSevenPressed(ActionEvent event) {
        gameboard.modifyPlayer('7', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btSixPressed(ActionEvent event) {
        gameboard.modifyPlayer('6', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btFivePressed(ActionEvent event) {
        gameboard.modifyPlayer('5', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btFourPressed(ActionEvent event) {
        gameboard.modifyPlayer('4', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btNinePressed(ActionEvent event) {
        gameboard.modifyPlayer('9', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btDPressed(ActionEvent event) {
        gameboard.modifyPlayer('D', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btCPressed(ActionEvent event) {
        gameboard.modifyPlayer('C', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btEPressed(ActionEvent event) {
        gameboard.modifyPlayer('E', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    private void btFPressed(ActionEvent event) {
        gameboard.modifyPlayer('F', player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }
}
