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
public class Arbol {

    private NodoEstado cabeza, ultimo;
    private int cantidadEstados;
    private int profundidad;

    public Arbol(int profundidad) {
        cabeza = null;
        ultimo = null;
        cantidadEstados = 0;
        this.profundidad = profundidad;
    }

    public void adicionarEstado(NodoEstado nuevo) {

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        cantidadEstados++;
        
        
    }

    public boolean buscarEstadoB(int id) {//Busca el estado, retirna un booleano
        boolean encontrado = false;

        if (cabeza == null) {
            System.out.println("\"La lista se encuentra vacia\"");
        } else {
            NodoEstado aux = cabeza;
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

    public NodoEstado buscarEstadoN(int id) {//busca el estado, retorna el nodo si lo encuentra
        NodoEstado aux;
        NodoEstado nodoEncontrado = null;
        boolean encontrado = false;
        if (cabeza == null) {
            //null
        } else {
            aux = cabeza;
            while (aux != null && !encontrado) {
                if (aux.getId() == id) {
                    encontrado = true;

                    nodoEncontrado = aux;

                } else {
                    aux = aux.getSiguiente();
                }

            }

        }
        
        if (nodoEncontrado==null) {
            System.out.println("No se encontro el nodo");
        }

        return nodoEncontrado;

    }

    public void agregarEstado(NodoEstado nuevo) {
        //Agrega el estado al arbol en su respectiva ubicacion
        if (nuevo.getId() != 0) {//0 representa el nodo raiz

            if (buscarEstadoB(nuevo.getIdpadre())) {
                
                unirPadreHijo(nuevo);
            } else {

                System.out.println("No se encontro al padre");
            }
        }
        adicionarEstado(nuevo);
        
    }

    private void unirPadreHijo(NodoEstado e) {
        //Ubica y enlaza el nuevo estado con su padreEstado
        ListaNodoHijo hijos = new ListaNodoHijo();
        NodoHijo nuevo = new NodoHijo(e.getId(), e.getIdpadre(), e.getValor(), e.getValorAcumulado(), e.getNivel());
        NodoEstado padre=buscarEstadoN(e.getIdpadre());
        if ( padre.getEnlaceHijo()== null) {
            hijos.adicionarHijo(nuevo);
            padre.setEnlaceHijo(hijos.getCabeza());
            
        } else {
            hijos.setCabeza(padre.getEnlaceHijo());
            
            hijos.adicionarHijo(nuevo);
            
        }

    }

    public void copiarDatoHijo(NodoEstado aux){
        NodoEstado auxPadre;
        NodoHijo auxHijo;
        ListaNodoHijo hijos = new ListaNodoHijo();
        
        auxPadre=buscarEstadoN(aux.getIdpadre());
        hijos.setCabeza(auxPadre.getEnlaceHijo());
        //auxPadre.setEnlaceHijo(hijos.getCabeza());
        auxHijo=hijos.buscarHijoN(aux.getId());
        auxHijo.setValorAcumulado(aux.getValorAcumulado());
        
    }
    
    public NodoEstado estadoIdeal() {
        NodoEstado aux;
        
        NodoHijo mejorHijo = null;
        NodoHijo EstadoIdeal = null;
        ListaNodoHijo hijos = new ListaNodoHijo();
        if (cabeza == null) {
            System.out.println("Arbol de evnetos vacio");
            return null;
        } else {
            aux = cabeza;
            int i=profundidad-1;
                
                while (aux!= null&&i>0) {
                    
                    if (aux.getNivel() == i) {
                        hijos.setCabeza(aux.getEnlaceHijo());
                        //aux.setEnlaceHijo(hijos.getCabeza());
                        mejorHijo=hijos.hijoMasValioso(cambioTurno(aux.getEstado().getTurno()));
                        
                        aux.setValorAcumulado(aux.getValor()+mejorHijo.getValorAcumulado());
                        
                        if (aux.getNivel()!=0) {
                            copiarDatoHijo(aux);
                        }
                        
                        if (aux.getSiguiente().getNivel()==i+1) {
                            i--;
                           aux = cabeza; 
                        }
                        
                        System.out.println("hijo");
                        System.out.println("valor acumulado:");
                        
                    } 
                        aux = aux.getSiguiente();
                    

                }
            
            
            aux = cabeza;
            hijos.setCabeza(aux.getEnlaceHijo());
            //aux.setEnlaceHijo(hijos.getCabeza());
            EstadoIdeal=hijos.hijoMasValioso(cambioTurno(aux.getEstado().getTurno()));
            
            return buscarEstadoN(EstadoIdeal.getId());
        }
        
        //return nodoEncontrado;
    }

    public int calcularTurnoH(int turnoInicial, int profundidad) {//calcular turno de hojas

        if (profundidad % 2 == 0) {
            return turnoInicial;
        } else {
            return cambioTurno(turnoInicial);
        }
    }

    public int cambioTurno(int t) {
        if (t == 2) {
            t = 3;
        } else {
            t = 2;
        }
        return t;
    }

    public int getCantidadEstados() {
        return cantidadEstados;
    }
    
    public NodoEstado estadoAleatorio(){
        NodoEstado aux;
        
        NodoHijo mejorHijo = null;
        NodoHijo EstadoIdeal = null;
        ListaNodoHijo hijos = new ListaNodoHijo();
        if (cabeza == null) {
            System.out.println("Arbol de evnetos vacio");
            return null;
        } else {
            aux = cabeza;
            int i=profundidad-1;
                
                while (aux!= null&&i>0) {
                    
                    if (aux.getNivel() == i) {
                        hijos.setCabeza(aux.getEnlaceHijo());
                        //aux.setEnlaceHijo(hijos.getCabeza());
                        mejorHijo=hijos.hijoAleatorio(cambioTurno(aux.getEstado().getTurno()));
                        
                        aux.setValorAcumulado(aux.getValor()+mejorHijo.getValorAcumulado());
                        
                        if (aux.getNivel()!=0) {
                            copiarDatoHijo(aux);
                        }
                        
                        if (aux.getSiguiente().getNivel()==i+1) {
                            i--;
                           aux = cabeza; 
                        }
                        
                        System.out.println("hijo");
                        System.out.println("valor acumulado:");
                        
                    } 
                        aux = aux.getSiguiente();
                    

                }
            
            
            aux = cabeza;
            hijos.setCabeza(aux.getEnlaceHijo());
            //aux.setEnlaceHijo(hijos.getCabeza());
            EstadoIdeal=hijos.hijoAleatorio(cambioTurno(aux.getEstado().getTurno()));
            
            return buscarEstadoN(EstadoIdeal.getId());
        }
        
    }
    

}
