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

public class SistemaProduccion {

    Arbol arbol;

    Estado eInicial;
    private NodoEstado eFinal;
    NodoEstado nodoInicial;
    int faseInicial;
    int profundidad;
    Jugada jugadaAnt;

    public SistemaProduccion(int profunidad, Estado eInicial, int faseInicial) {
    //faseIncial en que estado se va ejecutar el evento en el estado 
        arbol = new Arbol(profunidad);
        
        this.eInicial = eInicial;
        nodoInicial = new NodoEstado(0, -1, -1, 0, eInicial);
        arbol.agregarEstado(nodoInicial);
        this.faseInicial = faseInicial;
        this.profundidad = profunidad;
        
        
        generarEstados();
        generarEFinal();
    }
    
    public SistemaProduccion(Estado eInicial, int faseInicial) {
    //faseIncial en que estado se va ejecutar el evento en el estado 
        System.out.println("ESTOY AQUI");
        arbol = new Arbol(1);
        
        this.eInicial = eInicial;
        nodoInicial = new NodoEstado(0, -1, -1, 0, eInicial);
        arbol.agregarEstado(nodoInicial);
        this.faseInicial = faseInicial;
        this.profundidad = 1;
        
        
        generarEstados();
        generarEAleatorio();
    }

    public NodoEstado geteFinal() {
        return eFinal;
    }
    
    
    private void generarEFinal(){
        eFinal=arbol.estadoIdeal();
        
        
        System.out.println("ESTADO IDEAL");
        mostrar(eFinal);
        
    }
    
    private void generarEAleatorio(){
        eFinal=arbol.estadoAleatorio();
        
        
        System.out.println("ESTADO ALEATORIO");
        mostrar(eFinal);
        
    }
    
    public void mostrar(NodoEstado e){
        
        System.out.println("-----------------------------");
        System.out.println("Evento:"+e.getId());
        System.out.println("Nivel:"+e.getNivel());
        
        for (int i = 0; i < 7; i++) {
            System.out.println("");
            for (int j = 0; j < 7; j++) {
                
                System.out.print(" "+e.getEstado().getTablero()[i][j]);
            }
        }
         
         System.out.println(" ");
         System.out.print("Jugada:");
         System.out.print(" x:"+e.getEstado().getJugada().getXm());
         System.out.println(" y:"+e.getEstado().getJugada().getYm());
         System.out.println("Fase:"+e.getEstado().getJugada().getFase());
         System.out.println("Turno:"+e.getEstado().getTurno());
         System.out.println("Valor:"+e.getValor());
         System.out.println("Valor:"+e.getValorAcumulado());
         System.out.println(" -----------------------------");
    }
    
    private void generarEstados() {
        NodoEstado nodoInic = nodoInicial;
        ListaNodoHijo hijosX = new ListaNodoHijo();
        Estado estadoI = eInicial;
        int fase = faseInicial;
        int id = 0;
        int nivel = 1;
        boolean stop = true;
        int tablero[][];

        while (nivel <= profundidad &&
               estadoI.getFichas().Victoria(estadoI.getTurno())==0) {
            System.out.println("Repetido!!!");
            tablero = estadoI.getTablero();
            Jugada jugadaX;
            Evento eventoX;
            Estado estadoX;
            NodoEstado nodoX=null;
            int valorX;
            System.out.println("_________________________________NIVEL:"+nivel);
            if (fase == 1) {
                System.out.println("**************FASE INICIAL:"+fase);
                
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (tablero[i][j] == 1) {
                            id++;
                            jugadaX = new Jugada(1, i, j);
                            System.out.println("ID Estado Inicial:"+nodoInic.getId());
                            
                            if (nivel==2) {
                                mostrar(nodoInic);
                                System.out.println("--------------------------");
                            }
                             System.out.println("ID Estado Inicial Turno:"+nodoInic.getEstado().getTurno());
                            eventoX = new Evento(estadoI, jugadaX);//hacer copia del tablero
                            
                            valorX = funTran(eventoX);
                            estadoX = new Estado(eventoX.getEf().getTablero(),
                                    eventoX.getEf().getTurno(),
                                    eventoX.getEf().getFichas(),
                                    eventoX.getEf().getFichasElim());
                            
                                estadoX.setJugada(jugadaX);
                            
                            nodoX = new NodoEstado(id, nodoInic.getId(), valorX, nivel, estadoX);
                            mostrar(nodoX);
                            arbol.agregarEstado(nodoX);
                            
                            
                        }
                    }
                }
                
                if (nodoX.getEstado().getFichas().CambioFase()==1) {
                    fase=2;
                }
                
                

                System.out.println("---------->FASE FINAL:"+fase);

            } else {//fase 2
                
                Tablero tab = new Tablero();
                tab.tablero = cM(tablero);
                System.out.println("TABLERO:");
                tab.mostrarTablero();
                int turno = arbol.cambioTurno(nodoInic.getEstado().getTurno());
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        
                        if (tab.tablero[i][j] == turno) {
                            System.out.println("_______________(x,y)="+i+", "+j);
                            int x = -1;
                            int y = -1;
                            tab.verPosi_Cercana(turno, i,j);
                            
                            
                            if (tab.maq.x_a != 10 && tab.maq.y_a != 10) {
                                id++;
                                x = tab.maq.x_a;
                                y = tab.maq.y_a;
                                jugadaX = new Jugada(2, i, j, x, y);
                                eventoX = new Evento(estadoI, jugadaX);
                                valorX = funTran(eventoX);
                                
                                estadoX = new Estado(eventoX.getEf().getTablero(),
                                        eventoX.getEf().getTurno(),
                                        eventoX.getEf().getFichas(),
                                        eventoX.getEf().getFichasElim());
                                
                                    estadoX.setJugada(jugadaX);
                                
                                nodoX = new NodoEstado(id, nodoInic.getId(), valorX, nivel, estadoX);
                                mostrar(nodoX);
                                /*
                                System.out.println("JUGADA ANTERIOR X: "+jugadaAnt.getXo());
                                System.out.println("JUGADA ANTERIOR Y: "+jugadaAnt.getYo());
                                System.out.println("JUGADA ACTUAL X: "+nodoX.getEstado().getJugada().getXm());
                                System.out.println("JUGADA ACTUAL Y: "+nodoX.getEstado().getJugada().getYm());
                                if(jugadaAnt.getXo()==nodoX.getEstado().getJugada().getXm() && jugadaAnt.getYo()==nodoX.getEstado().getJugada().getYm()){
                                    if(eventoX.getEf().getTurno()==2){
                                        nodoX.setValorAcumulado(-1000);
                                    }else if(eventoX.getEf().getTurno()==3){
                                        nodoX.setValorAcumulado(1000);
                                    }
                                }*/
                                arbol.agregarEstado(nodoX);
                            }
                            if (tab.maq.x_d != 10 && tab.maq.y_d != 10) {
                                id++;
                                x = tab.maq.x_d;
                                y = tab.maq.y_d;
                                jugadaX = new Jugada(2, i, j, x, y);
                                eventoX = new Evento(estadoI, jugadaX);
                                valorX = funTran(eventoX);
                                estadoX = new Estado(eventoX.getEf().getTablero(),
                                        eventoX.getEf().getTurno(),
                                        eventoX.getEf().getFichas(),
                                        eventoX.getEf().getFichasElim());
                                
                                    estadoX.setJugada(jugadaX);
                                
                                nodoX = new NodoEstado(id, nodoInic.getId(), valorX, nivel, estadoX);
                                mostrar(nodoX);
                                /*
                                System.out.println("JUGADA ANTERIOR X: "+jugadaAnt.getXo());
                                System.out.println("JUGADA ANTERIOR Y: "+jugadaAnt.getYo());
                                System.out.println("JUGADA ACTUAL X: "+nodoX.getEstado().getJugada().getXm());
                                System.out.println("JUGADA ACTUAL Y: "+nodoX.getEstado().getJugada().getYm());
                                if(jugadaAnt.getXo()==nodoX.getEstado().getJugada().getXm() && jugadaAnt.getYo()==nodoX.getEstado().getJugada().getYm()){
                                    if(eventoX.getEf().getTurno()==2){
                                        nodoX.setValorAcumulado(-1000);
                                    }else if(eventoX.getEf().getTurno()==3){
                                        nodoX.setValorAcumulado(1000);
                                    }
                                }*/
                                arbol.agregarEstado(nodoX);
                            }
                            if (tab.maq.x_b != 10 && tab.maq.y_b != 10) {
                                System.out.println("Entro abajo"+i+", :"+j);
                                id++;
                                x = tab.maq.x_b;
                                y = tab.maq.y_b;
                                jugadaX = new Jugada(2, i, j, x, y);
                                eventoX = new Evento(estadoI, jugadaX);
                                valorX = funTran(eventoX);
                                estadoX = new Estado(eventoX.getEf().getTablero(),
                                        eventoX.getEf().getTurno(),
                                        eventoX.getEf().getFichas(),
                                        eventoX.getEf().getFichasElim());
                               
                                    estadoX.setJugada(jugadaX);
                                
                                nodoX = new NodoEstado(id, nodoInic.getId(), valorX, nivel, estadoX);
                                mostrar(nodoX);
                                /*
                                System.out.println("JUGADA ANTERIOR X: "+jugadaAnt.getXo());
                                System.out.println("JUGADA ANTERIOR Y: "+jugadaAnt.getYo());
                                System.out.println("JUGADA ACTUAL X: "+nodoX.getEstado().getJugada().getXm());
                                System.out.println("JUGADA ACTUAL Y: "+nodoX.getEstado().getJugada().getYm());
                                if(jugadaAnt.getXo()==nodoX.getEstado().getJugada().getXm() && jugadaAnt.getYo()==nodoX.getEstado().getJugada().getYm()){
                                    if(eventoX.getEf().getTurno()==2){
                                        nodoX.setValorAcumulado(-1000);
                                    }else if(eventoX.getEf().getTurno()==3){
                                        nodoX.setValorAcumulado(1000);
                                    }
                                }
                                */
                                arbol.agregarEstado(nodoX);
                            }
                            if (tab.maq.x_i != 10 && tab.maq.y_i != 10) {
                                id++;
                                x = tab.maq.x_i;
                                y = tab.maq.y_i;
                                jugadaX = new Jugada(2, i, j, x, y);
                                eventoX = new Evento(estadoI, jugadaX);
                                valorX = funTran(eventoX);
                                estadoX = new Estado(eventoX.getEf().getTablero(),
                                        eventoX.getEf().getTurno(),
                                        eventoX.getEf().getFichas(),
                                        eventoX.getEf().getFichasElim());
                                
                                    estadoX.setJugada(jugadaX);
                                
                                nodoX = new NodoEstado(id, nodoInic.getId(), valorX, nivel, estadoX);
                                mostrar(nodoX);
                                /*
                                System.out.println("JUGADA ANTERIOR X: "+jugadaAnt.getXo());
                                System.out.println("JUGADA ANTERIOR Y: "+jugadaAnt.getYo());
                                System.out.println("JUGADA ACTUAL X: "+nodoX.getEstado().getJugada().getXm());
                                System.out.println("JUGADA ACTUAL Y: "+nodoX.getEstado().getJugada().getYm());
                                if(jugadaAnt.getXo()==nodoX.getEstado().getJugada().getXm() && jugadaAnt.getYo()==nodoX.getEstado().getJugada().getYm()){
                                    if(eventoX.getEf().getTurno()==2){
                                        nodoX.setValorAcumulado(-1000);
                                    }else if(eventoX.getEf().getTurno()==3){
                                        nodoX.setValorAcumulado(1000);
                                    }
                                }
                                */
                                arbol.agregarEstado(nodoX);
                            }
                            

                        }
                    }
                }
                

            }/*
            if ((nodoInic.getNivel() + 1) == nodoInic.getSiguiente().getNivel()) {
                nivel++;
            }*/
            if ( nodoInic.getSiguiente()!=null) {
              if ((nodoInic.getNivel() + 1) == nodoInic.getSiguiente().getNivel()) {
                nivel++;
                }  
            }else{
                nivel=profundidad+1;
            }
            nodoInic = nodoInic.getSiguiente();
            estadoI=nodoInic.getEstado();
            System.out.println("ID Estado:"+nodoInic.getId());
            System.out.println("TURNO Estado: "+nodoInic.getEstado().getTurno());
        }

        System.out.println("Se termino!!!");
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
    public int[][] cMs(int m[][]) {
        int copia[][] = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                copia[i][j] = m[j][i];
            }
        }
        return copia;
    }

    public int evaluar2EnLinea(Evento e) {
        int turno = arbol.cambioTurno(e.getE().getTurno());
        int valor = e.evaluar2EnLinea();
        if (valor != 0) {
            switch(turno){
                case 3:
                    return 1*valor;
                case 2:
                    return -1*valor;
                default:
                    return 0;
            }/*
            return switch (turno) {
                case 3 ->
                    1 * valor;
                case 2 ->
                    -1 * valor;
                default ->
                    0;
            };*/
        } else {
            //System.out.println("No realiza 2 en linea");
            return 0;
        }

    }

    public int evaluar3EnLinea(Evento e) {
        int turno = arbol.cambioTurno(e.getE().getTurno());
        int valor = e.evaluar3EnLinea();
        if (valor != 0) {
            switch(turno){
                case 3:
                    return 5*valor;
                case 2:
                    return -5*valor;
                default:
                    return 0;
            }/*
            return switch (turno) {
                case 3 ->
                    3 * valor;
                case 2 ->
                    -3 * valor;
                default ->
                    0;
            };*/
        } else {
            //System.out.println("No realiza 3 en linea");
            return 0;
        }
    }

    public int evaluarBloqueo(Evento e) {
        int turno = arbol.cambioTurno(e.getE().getTurno());
        int valor = e.evaluarBloqueo();
        if (valor != 0) {
            switch(turno){
                case 3:
                    return 3 * valor;
                case 2:
                    return -3 * valor;
                default:
                    return 0;
            }/*
            return switch (turno) {
                case 3 ->
                    2 * valor;
                case 2 ->
                    -2 * valor;
                default ->
                    0;
            };*/
        } else {
            //System.out.println("No realiza bloqueo");
            return 0;
        }
    }

    public int funTran(Evento e) {

        return evaluar2EnLinea(e) + evaluar3EnLinea(e) + evaluarBloqueo(e);
    }

}
