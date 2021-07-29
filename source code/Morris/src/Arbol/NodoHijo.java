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
public class NodoHijo {
   private int id;
   
   private int idpadre;
   private int valor;
   private int valorAcumulado;
   private int nivel;
   private NodoHijo siguiente;

    public NodoHijo(int id, int idpadre, int valor, int valorAcumulado, int nivel) {
        this.id = id;
        
        this.idpadre = idpadre;
        this.valor = valor;
        this.valorAcumulado = valorAcumulado;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(int idpadre) {
        this.idpadre = idpadre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(int valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public NodoHijo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHijo siguiente) {
        this.siguiente = siguiente;
    }
   
   
   
}
