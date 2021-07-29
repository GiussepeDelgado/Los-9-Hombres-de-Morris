/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;
import Juego.FiE;

public class FichasEliminadas {
    
    public ArrayList <FiE> f_e = new ArrayList<FiE>();
    
    public FichasEliminadas(){
        
    }
    public void AñadirPar3(int a[], int b[], int c[]){
        f_e.add(new FiE(a,b,c));
    }
    
    
}
