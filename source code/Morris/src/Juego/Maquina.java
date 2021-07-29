/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Maquina {
        public int [] esp_v_x = new int[24];
        public int [] esp_v_y = new int[24];
        public int [] f_facil_x = new int[9]; 
        public int [] f_facil_y = new int[9]; 
        public int x_d = 10;
        public int y_d = 10;
        public int x_i = 10;
        public int y_i = 10;
        public int x_a = 10;
        public int y_a = 10;
        public int x_b = 10;
        public int y_b = 10;
        
        public Maquina(){
    
        }
        public void Reset_p(){
            x_d = 10;
            y_d = 10;
            x_i = 10;
            y_i = 10;
            x_a = 10;
            y_a = 10;
            x_b = 10;
            y_b = 10;
        }
        
        public void mostrar(){
            System.out.println("x_a: "+x_i);
            System.out.println("y_a: "+y_i);
            System.out.println("x_d: "+x_a);
            System.out.println("y_d: "+y_a);
            System.out.println("x_b: "+x_d);
            System.out.println("y_b: "+y_d);
            System.out.println("x_i: "+x_b);
            System.out.println("x_i: "+y_b);
            
            
        }
        
}
