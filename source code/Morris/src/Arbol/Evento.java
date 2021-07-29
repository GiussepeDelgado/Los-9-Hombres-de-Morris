/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

/**
 *
 * @author Windows 10 Pro
 */
import Juego.*;
import java.util.ArrayList;

public class Evento {

    private Estado e;
    private Jugada JugadaA;
    private Estado ef;

    public Evento(Estado e, Jugada JugadaA) {
        this.e = e;
        this.JugadaA = JugadaA;
        generarEFinal();
    }

    private void generarEFinal() {
        int fase = JugadaA.getFase();
        
        int turno = cambioTurno(e.getTurno());
        
        int tablero[][] = cM(e.getTablero());
        Tablero tab = new Tablero();
        tab.tablero = tablero;
        FichasEliminadas fichasEli;
        Ficha fichas= new Ficha();
        fichas.FICHAS_B=cM(e.getFichas().FICHAS_B);
        fichas.FICHAS_N=cM(e.getFichas().FICHAS_N);
        fichas.FICHA_B_Col=e.getFichas().FICHA_B_Col;
        fichas.FICHA_B_Com=e.getFichas().FICHA_B_Com;
        fichas.FICHA_N_Col=e.getFichas().FICHA_N_Col;
        fichas.FICHA_N_Com=e.getFichas().FICHA_N_Com;
       
         
        
        ArrayList<FiE> listaCopiada = ((ArrayList<FiE>) e.getFichasElim().f_e.clone());
        tab.f.f_e = listaCopiada;
        
        if (fase == 1) {
            
            tab.ColocarFicha(JugadaA.getXm(), JugadaA.getYm(), turno);
            fichas.FichaColocada(1, turno);
            System.out.println("-------->>>Coloco ficha:"+turno);
            if (tab.comerF(turno) == 1) {
                //faalta implementar n fichas comidas
                
                
                    tab.Com_F_F(cambioTurno(turno));//elimina ficha del rival
                    fichas.EliminarFicha(cambioTurno(turno));
                
                
                

            }

        } else {
            tab.moverA(JugadaA.getXo(), JugadaA.getYo(), JugadaA.getXm(), JugadaA.getYm(), turno);
            
            if (tab.comerF(turno) == 1) {
                
                
                    tab.Com_F_F(cambioTurno(turno));//elimina ficha del rival
                    fichas.EliminarFicha(cambioTurno(turno));
                
                
            }

        }
        
        
        
        
        
        fichasEli=tab.f;
        
        ef = new Estado(tab.tablero, turno,fichas,fichasEli);
        System.out.println("-------->>> ficha:"+turno);
        
        
        ef.setFaseFutura(fichas.CambioFase()+1);
        
        
    }
    
    
    
   

    public int cambioTurno(int t) {
        if (t == 2) {
            t = 3;
        } else {
            t = 2;
        }
        return t;
    }

    public int[][] cM(int m[][]) {
        int copia[][] = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                copia[i][j] = m[i][j];
            }
        }
        return copia;
    }

    public int[] cM(int m[]) {
        int copia[] = new int[9];
        for (int i = 0; i < 9; i++) {

            copia[i] = m[i];

        }
        return copia;
    }

    public int evaluar2EnLinea(){
        int xm,ym,xo,yo;
        int a;
        int [][]m = new int [7][7];
        Tablero Tab = new Tablero();
            m = e.getTablero();              //Igualamos los tableros
            for(int a1=0;a1<7;a1++){
                for(int a2=0;a2<7;a2++){
                      Tab.tablero[a1][a2]=m[a1][a2];
                }
            }
        if(JugadaA.getFase()==1){
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();
            Tab.ColocarFicha(xm, ym, cambioTurno(e.getTurno()));    //Ejecutamos la jugada para evaluar su conveniencia
        }else{
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();
            xo=JugadaA.getXo();
            yo=JugadaA.getYo();
            Tab.moverA(xo, yo, xm, ym, cambioTurno(e.getTurno()));
        }
        a = Tab.Evaluar_general_1(xm, ym, cambioTurno(e.getTurno()), 2);
        //Retorna el vaor de la jugada
        
        /*
        System.out.println(" ----------------");
        
        Tab.mostrarTablero();
        System.out.println(" ");
        System.out.println("cantidad 2: "+a);
        System.out.println(" ----------------");
        
        */
        return a;
    }  

    public int evaluar3EnLinea(){
        int xm,ym,xo,yo;
        int a;
        int [][]m = new int [7][7];
        Tablero Tab = new Tablero();
        m = e.getTablero();              //Igualamos los tableros
            for(int a1=0;a1<7;a1++){
                for(int a2=0;a2<7;a2++){
                    Tab.tablero[a1][a2]=m[a1][a2];
                }
            }
        if(JugadaA.getFase()==1){
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();       //Igualamos los tableros
            Tab.ColocarFicha(xm, ym, cambioTurno(e.getTurno()));    //Ejecutamos la jugada para evaluar su conveniencia
        }else{
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();
            xo=JugadaA.getXo();
            yo=JugadaA.getYo();
            Tab.moverA(xo, yo, xm, ym, cambioTurno(e.getTurno()));
        }
        a = Tab.Evaluar_general_1(xm, ym, cambioTurno(e.getTurno()), 3);
        //Retorna el valor de la jugada
        /*
        System.out.println(" ----------------");
        Tab.mostrarTablero();
        System.out.println(" ");
        System.out.println("cantidad 3: "+a);
        System.out.println(" ----------------");*/
        return a;
    }

    public int evaluarBloqueo(){
        int xm,ym,xo,yo;
        int a;
        Tablero Tab = new Tablero();
        int [][]m = new int [7][7];
        m=e.getTablero();
            for(int a1=0;a1<7;a1++){
                for(int a2=0;a2<7;a2++){
                    Tab.tablero[a1][a2]=m[a1][a2];
                }
            }
        if(JugadaA.getFase()==1){
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();
            Tab.ColocarFicha(xm, ym, cambioTurno(e.getTurno()));    //Ejecutamos la jugada para evaluar su conveniencia
        }else{
            xm=JugadaA.getXm();
            ym=JugadaA.getYm();
            xo=JugadaA.getXo();
            yo=JugadaA.getYo();
            Tab.moverA(xo, yo, xm, ym, cambioTurno(e.getTurno()));
        }
        a = Tab.Evaluar_bloqueo_1(xm, ym, cambioTurno(e.getTurno()));
        //Retorna la cantidad de bloqueos que se hacen
        /*
        System.out.println(" ----------------");
        Tab.mostrarTablero();
        System.out.println(" ");
        System.out.println("cantidad B: "+a);
        System.out.println(" ----------------");*/
        return a;
    }

    public Estado getE() {
        return e;
    }

    public void setE(Estado e) {
        this.e = e;
    }

    public Jugada getJugadaA() {
        return JugadaA;
    }

    public void setJugadaA(Jugada JugadaA) {
        this.JugadaA = JugadaA;
    }

    public Estado getEf() {
        return ef;
    }

    public void setEf(Estado ef) {
        this.ef = ef;
    }

}
