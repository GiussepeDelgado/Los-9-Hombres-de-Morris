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
public class Jugada {
    private int fase;
    private int xm,ym,xo,yo;
    
    public Jugada(int fase,int x,int y){//Fase 1
        this.xm=x;
        this.ym=y;
        this.xo=-1;
        this.yo=-1;
        this.fase=fase;
    }
    public Jugada(int fase,int xo,int yo,int xm,int ym){//Fase 2
        this.xo=xo;
        this.yo=yo;
        this.xm=xm;
        this.ym=ym;
        this.fase=fase;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getXm() {
        return xm;
    }

    public void setXm(int xm) {
        this.xm = xm;
    }

    public int getYm() {
        return ym;
    }

    public void setYm(int ym) {
        this.ym = ym;
    }

    public int getXo() {
        return xo;
    }

    public void setXo(int xo) {
        this.xo = xo;
    }

    public int getYo() {
        return yo;
    }

    public void setYo(int yo) {
        this.yo = yo;
    }
    
    
    
}
