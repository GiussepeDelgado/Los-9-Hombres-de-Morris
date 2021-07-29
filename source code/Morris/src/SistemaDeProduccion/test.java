/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaDeProduccion;

/**
 *
 * @author Windows 10 Pro
 */
import Arbol.*;
import Juego.*;
public class test {
    
    
    
    
    
    
    public static void main(String args[]){
        
        Tablero tab=new Tablero();
        
        tab.tablero[1][3]=2;
        tab.tablero[6][0]=2;
        tab.tablero[0][6]=2;
        
        
        tab.tablero[0][0]=3;
        tab.tablero[2][2]=3;
        tab.tablero[3][1]=3;
        
        tab.mostrarTablero();
        Ficha fichas= new Ficha();
        FichasEliminadas fichasEliminadas= new FichasEliminadas();
        Estado inicial= new Estado(tab.tablero,3,fichas,fichasEliminadas);
        //Jugada jugada= new Jugada(1, 2, 2);
        
       SistemaProduccion sp=new SistemaProduccion(3,inicial,2);
        
       System.out.println("Eventos Generados:"+sp.arbol.getCantidadEstados());
        
        
        /*
        int a[][]= new int[3][3];
        int c=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c++;
                a[i][j]=c;
                
            }
        }
        
        for (int i = 0; i < 3; i++) {
            
            for (int j = 0; j < 3; j++) {
                System.out.print(" "+a[j][i]);
                
            }
            System.out.println("");
        }
        
        System.out.println("");
*/
        
    }
}
