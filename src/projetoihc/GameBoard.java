/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoihc;

import java.util.Random;

/**
 *
 * @author carol
 */
public class GameBoard {
    
    private char[][] solution;
    private char[][] initial;
    private char[][] player;
    
    GameBoard(){
        
        initial = new char[16][16];
        player=new char[16][16];
        int counter=1,k1,k2;
        switch (Utilizador.getChoselvl()){
            case 1:
                generate(192);
                break;
            case 2:
                generate(128);
                break;
            case 3:
                generate(64);
        }
        player=initial;
    }
    
    public char[][] getSolution() {
	return solution;
    }

    public char[][] getInitial() {
	return initial;
    }

    public char[][] getPlayer() {
	return player;
    }

    public void modifyPlayer(char val, int row, int col) {
            if(initial[row][col]=='\0')
                player[row][col] = val;		
    }
        
    public void generate(int dificulty){
        int k=0;
        int triesBoard=0;
        int triesInsert=0;
        int ticked=0;
        while(ticked!=dificulty){
            for(int i=0;i<16;i++){
                for(int j=0;j<16;j++){
                    if(Math.random()*101<30){
                        k=(int)(Math.random()*16);
                        if(initial[i][j]=='\0' && unUsedInBox(findStart(i),findStart(j),Character.forDigit(k, 16),initial)&&unUsedInRow(i,Character.forDigit(k, 16),initial)&&unUsedInCol(j,Character.forDigit(k, 16),initial)){
                            initial[i][j]=Character.forDigit(k, 16);
                            ticked++;
                            if(ticked==dificulty)
                                break;
                        }  
                    }
                }
                if(ticked==dificulty)
                    break;
            }
        }
    }
	
    static int findStart(int i){
	int resto=i%4;
	if(resto!=0)
            return (i-resto);
	return i;		
    }
        
    public void row_change(int k1,int k2)
{
    char temp;
   for(int n=1;n<=4;n++)
   {
      for(int i=0;i<16;i++)
      {
         temp=initial[k1][i];
         initial[k1][i]=initial[k2][i];
         initial[k2][i]=temp;
      }
      k1++;
      k2++;
   }
}
public void col_change(int k1,int k2)
{
   char temp;
   for(int n=1;n<=4;n++)
   {
      for(int i=0;i<16;i++)
      {
         temp=initial[i][k1];
         initial[i][k1]=initial[i][k2];
         initial[i][k2]=temp;
      }
      k1++;
      k2++;
   }
}
boolean unUsedInBox(int rowStart,int colStart,char num,char [][] matrix){
    for(int i=0;i<4;i++)
        for(int j=0;j<4;j++)
            if(matrix[rowStart+i][colStart+j]==num)
                return false;
    return true;
	}

boolean unUsedInRow(int i,char num,char [][] matrix){
    for(int j=0;j<16;j++)
        if(matrix[i][j]==num)
            return false;
    return true;
	}

boolean unUsedInCol(int j,char num,char [][] matrix){
    for(int i=0;i<16;i++)
        if(matrix[i][j]==num)
            return false;
    return true;
	}

public boolean verify(){
    for(int i=0;i<16;i++){
        for(int j=0;j<16;j++){
            if(!(unUsedInBox(i,j,player[i][j],player) && unUsedInRow(i,player[i][j],player) && unUsedInCol(j,player[i][j],player))){
                System.out.println("Acabei falso");
                return false;
            }
                
        }
    }
    System.out.println("Acabei verdade");
    return true;
}

}