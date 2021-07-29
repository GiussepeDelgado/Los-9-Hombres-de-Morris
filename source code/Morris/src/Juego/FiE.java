/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author user
 */
public class FiE{ 
    //Eliminar
    public int [] cordenada1 = new int [2];
    public int [] cordenada2 = new int [2];
    public int [] cordenada3 = new int [2];

    public FiE(int a[],int b[], int c[] ) {
        cordenada1=a;
        cordenada2=b;
        cordenada3=c;
    }
    public void imprimir(){
        System.out.println("Las cordenadas son: "+cordenada1[0]+" "+cordenada1[1]);
        System.out.println("Las cordenadas son: "+cordenada2[0]+" "+cordenada2[1]);
        System.out.println("Las cordenadas son: "+cordenada3[0]+" "+cordenada3[1]);
    }
    
}
