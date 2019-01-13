/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoihc;

/**
 *
 * @author carol
 */
public class Utilizador {
    
    private static String nome;
    private static String pass;
    private static int Level1;
    private static int Level2;
    private static int Level3;
    private static int choselvl;
    
    Utilizador(String username, String password){
        
        this.nome=username;
        this.pass=password;
        this.Level1=0;
        this.Level2=0;
        this.Level3=0;
        choselvl=1;
          
    }

    public static void setChoselvl(int choselvl) {
        Utilizador.choselvl = choselvl;
    }

    public static int getChoselvl() {
        return choselvl;
    }

    public static String getNome() {
        return nome;
    }

    public static String getPass() {
        return pass;
    }

    public static float getLevel1() {
        return Level1;
    }

    public static float getLevel2() {
        return Level2;
    }

    public static float getLevel3() {
        return Level3;
    }

    public static void setNome(String nome) {
        Utilizador.nome = nome;
    }

    public static void setPass(String pass) {
        Utilizador.pass = pass;
    }

    public static void setLevel1(int Level1) {
        Utilizador.Level1 = Level1;
    }

    public static void setLevel2(int Level2) {
        Utilizador.Level2 = Level2;
    }

    public static void setLevel3(int Level3) {
        Utilizador.Level3 = Level3;
    }
    
    
 
}
