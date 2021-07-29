/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Configuracion.Sistema;

//Los arrays de fichas representan el estado de cada una de ellas.
//Si un elemento de dentro es 2 significa que aún no se ha colocado en el tablero
//Si es 1 significa que ya está en el tablero
//Si es 0 significa que fue comido y ya no está dentro del juego
public class Ficha {
    public int [] FICHAS_B = new int[Sistema.F_BLANCAS];
    public int [] FICHAS_N = new int[Sistema.F_NEGRAS];
    public int FICHA_B_Col = 0;
    public int FICHA_N_Col = 0;
    public int FICHA_B_Com = 0;
    public int FICHA_N_Com = 0;
    
    
    public Ficha(){
        //Indicando que las n fichas no han sido colocadas aún pero están en la mano del jugador
        for( int i = 0; i < Sistema.F_BLANCAS; i++){
            FICHAS_B[i] = 2;
        }
        for( int j = 0; j < Sistema.F_NEGRAS; j++){
            FICHAS_N[j] = 3;
        }
    }
    
    //Descontar una ficha del vector respectivo(pasarlo al estado 1[que indica que la ficha está en el tablero])
    public void FichaColocada(int a, int t){
        //Dependiendo del turno se empiezan a 
        if( a == 1){
          if( t ==2 ){
              FICHAS_B[FICHA_B_Col]=1;
              FICHA_B_Col++;
          }
          if( t ==3 ){
              FICHAS_N[FICHA_N_Col]=1;
              FICHA_N_Col++;
          }
        }
    }
    
    //Eliminar fichas del juego
    public void EliminarFicha(int t){
        //Dependiendo del turno se empiezan a 
          if( t == 2 ){
              FICHAS_B[FICHA_B_Com]=0;
              FICHA_B_Com++;
          }
          if( t == 3 ){
              FICHAS_N[FICHA_N_Com]=0;
              FICHA_N_Com++;
          }
    }
    
    //Condición de victoria
    public int Victoria(int t){
        if( t == 2 ){
            //Si la cantidad de fichas negras comidas es 7
            if(FICHA_N_Com == 5){
                return 1;//Indica que es victoria del jugador 1 (fichas blancas)
            }
        }
        if( t == 3 ){
            //Si la cantidad de fichas blancas comidas es 7
            if(FICHA_B_Com == 5){
                return 2;//Indica que es victoria del jugador 2 (fichas negras)
            }
        }
        return 0;//No hay victoria de nadie aún
    }
    
    public int CambioFase(){
        if(FICHAS_B[Sistema.F_BLANCAS-1]==1 && FICHAS_N[Sistema.F_NEGRAS-1]==1){
            return 1;//Indica que la última ficha ya fue colocada por lo que se cambia de fase
        }else{
            return 0;
        }
    }
}
