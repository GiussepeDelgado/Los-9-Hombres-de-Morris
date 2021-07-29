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
public class Estado {

    private int tablero[][];
    private int turno;
    private int faseFutura;
    private Ficha fichas;
    private FichasEliminadas fichasElim;
    private Jugada jugada; //Jugada que determino al tablero guardado

    public Estado(int tablero[][], int turno,Ficha fichas,FichasEliminadas fichasElim) {

        this.tablero = tablero;
        this.turno = turno;
        this.fichas=fichas;
        this.jugada = null;
        this.fichasElim=fichasElim;

    }

    public FichasEliminadas getFichasElim() {
        return fichasElim;
    }

    public void setFichasElim(FichasEliminadas fichasElim) {
        this.fichasElim = fichasElim;
    }

    
    
    public int[][] getTablero() {
        return tablero;
    }

    public Ficha getFichas() {
        return fichas;
    }

    public void setFichas(Ficha fichas) {
        this.fichas = fichas;
    }

   

    public int getTurno() {
        return turno;
    }

    public Jugada getJugada() {
        return jugada;
    }

    public void setJugada(Jugada jugada) {
        this.jugada = jugada;
    }

    public int getFaseFutura() {
        return faseFutura;
    }

    public void setFaseFutura(int faseFutura) {
        this.faseFutura = faseFutura;
    }

}
