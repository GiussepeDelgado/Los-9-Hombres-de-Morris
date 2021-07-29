/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.util.ArrayList;

/**
 *
 * @author Windows 10 Pro
 */
public class ListaNodoHijo {

    private NodoHijo cabeza;
    public ListaNodoHijo() {

        cabeza = null;
    }

    public void adicionarHijo(NodoHijo nuevo) {

        NodoHijo aux;
        if (cabeza == null) {
            cabeza = nuevo;

        } else {

            aux = cabeza;

            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();

            }

            aux.setSiguiente(nuevo);

        }
        //System.out.println("---------->Se adiciono el hijo:"+nuevo.getId());

    }

    public boolean buscarHijo(int id) {
        boolean encontrado = false;

        if (cabeza == null) {
            System.out.println("\"La lista de hijos se encuentra vacia\"");
        } else {
            NodoHijo aux = cabeza;
            while (aux != null && !encontrado) {
                if (aux.getId() == id) {
                    encontrado = true;
                } else {
                    aux = aux.getSiguiente();
                }
            }

        }
        return encontrado;
    }
    public NodoHijo buscarHijoN(int id) {
        boolean encontrado = false;
        NodoHijo hijoEn=null;
        if (cabeza == null) {
            System.out.println("\"La lista de hijos se encuentra vacia\"");
        } else {
            NodoHijo aux = cabeza;
            while (aux != null && !encontrado) {
                if (aux.getId() == id) {
                    encontrado = true;
                    hijoEn=aux;
                } else {
                    aux = aux.getSiguiente();
                }
            }

        }
        return hijoEn;
    }

    public NodoHijo hijoMasValioso(int turno) {
        NodoHijo mejorHijo=null;
        ArrayList <NodoHijo> mejoresHijos=new ArrayList<NodoHijo>();
        int mayorValor=-1;
        if (cabeza == null) {
            System.out.println("\"La lista de hijos se encuentra vacia\"");
            return null;
        } else {
                NodoHijo aux = cabeza;
                mejorHijo=aux;
                mayorValor = aux.getValorAcumulado();
                mejoresHijos.add(aux);
                aux = aux.getSiguiente();
            if (turno == 3) {//Negras max
               
                while (aux != null) {
                    
                    if (mayorValor==aux.getValorAcumulado()) {
                        mejoresHijos.add(aux);
                    }
                    if (mayorValor < aux.getValorAcumulado()) {
                        mejorHijo=aux;
                        mayorValor = aux.getValorAcumulado();
                        mejoresHijos.clear();
                        mejoresHijos.add(aux);
                    }
                    
                    
                    aux = aux.getSiguiente();
                }
            }else if(turno == 2){//Blancas min
                
                while (aux != null) {
                    if (mayorValor==aux.getValorAcumulado()) {
                        mejoresHijos.add(aux);
                    }
                    if (mayorValor > aux.getValorAcumulado()) {
                        mejorHijo=aux;
                        mayorValor = aux.getValorAcumulado();
                        mejoresHijos.clear();
                        mejoresHijos.add(aux);
                    }
                    aux = aux.getSiguiente();
                }
            }

        }
        
        int ubic=(int)(Math.random()*mejoresHijos.size());
        mejorHijo=mejoresHijos.get(ubic);
        
        return mejorHijo;
    }

    public NodoHijo getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoHijo cabeza) {
        this.cabeza = cabeza;
    }
    
    public NodoHijo hijoAleatorio(int turno) {
        //NodoHijo mejorHijo = null;
        ArrayList<NodoHijo> hijosAleatorios = new ArrayList<NodoHijo>();

        if (cabeza == null) {
            System.out.println("\"La lista de hijos se encuentra vacia\"");
            return null;
        } else {
            NodoHijo aux = cabeza;
            hijosAleatorios.add(aux);
            aux = aux.getSiguiente();
               
            while (aux != null) {

                hijosAleatorios.add(aux);
                    
                aux = aux.getSiguiente();

            }
            
            
        }
        int ind=(int)(Math.random()*hijosAleatorios.size());
        
        return hijosAleatorios.get(ind);
    }

    

    
}
