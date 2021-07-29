/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Configuracion.Sistema;
import Juego.*;


public class Tablero {
    public int [][] tablero = new int [Sistema.N_FILAS][Sistema.N_COLUMNAS];
    
    public int [][] a = new int [3][2];
    public FichasEliminadas f = new FichasEliminadas();
    public Maquina maq = new Maquina();
    public int b_col=0;
    //Para la maquina
    public int back_x;
    public int back_y;

    public Tablero() {
        for ( int i = 0; i < Sistema.N_FILAS; i++ ){
            for ( int j = 0; j < Sistema.N_COLUMNAS; j++){
                tablero[i][j] = 0;
            }
        }
        //Primera fila
        tablero[0][0]=1;
        tablero[0][3]=1;
        tablero[0][6]=1;
        //Segunda fila
        tablero[1][1]=1;
        tablero[1][3]=1;
        tablero[1][5]=1;
        //Tercera fila
        tablero[2][2]=1;
        tablero[2][3]=1;
        tablero[2][4]=1;
        //Fila media
        tablero[3][0]=1;
        tablero[3][1]=1;
        tablero[3][2]=1;
        tablero[3][3]=5;//Para indicar separación en la matriz
        tablero[3][4]=1;
        tablero[3][5]=1;
        tablero[3][6]=1;
        //Quinta fila
        tablero[4][2]=1;
        tablero[4][3]=1;
        tablero[4][4]=1;
        //Sexta fila
        tablero[5][1]=1;
        tablero[5][3]=1;
        tablero[5][5]=1;
        //Séptima fila
        tablero[6][0]=1;
        tablero[6][3]=1;
        tablero[6][6]=1;
    }

    public int[][] getTablero() {
        return tablero;
        }
    
    
    public int getPosicion(int a, int b){
        return tablero [a][b];
    }
    
    //Para la primera fase
    public int ColocarFicha(int a, int b, int t){
        
        if (tablero[a][b] == 1){
            tablero[a][b] = t;
            
            return 1;//Indica que se colocó la ficha en un lugar vacío
        }else{
            //System.out.println("---------->Se colcado la ficha");
            return 0;//Indica que no se pudo colocar la ficha
        }
        
        
    }//Debe retornar un valor indicando si se puede colocar o no una ficha ahí
    
     
    
    //Ver si la ficha seleccionada es tuya (2-fase) 2
    public int posiValido(int a, int b, int t){
        if( t == 2){
            if( tablero[a][b] == t){
                return 0;//Significa que es una posición suya(jugador 1).
            }
            else{
                return 1;//Significa que es una posición vacía.
            }
        }
        if( t == 3){
            if( tablero[a][b] == t){
                return 0;//Significa que es una posición suya(jugador 2).
            }
            else{
                return 1;//Significa que es una posición vacía.
            }
        }
        return 2; //Revisar el programa que algo está mal xd
    }
    
    
    
    public int comerF(int t){ //Verfica si come
        int p=0, q=0, l=0, m=0, n=6, c=0;
        //Falta detallar para las filas de en medio
        for( p = 0; p < Sistema.N_FILAS; p++ ){
            for( q = 0; q < Sistema.N_COLUMNAS; q++ ){
                if(tablero[p][q] == t){
                    a[c][0]=p;
                    a[c][1]=q;
                    c++;
                }
                if((tablero[p][q] != 0 && tablero[p][q] != t) || tablero[p][q] == 5){
                    c = 0;
                }
                if(c == 3){//Se puede comer
                    if(ComRep()==0){
                        System.out.println("vertical");
                        return 1;//Comer
                    }
                }
            }
            c=0;
        }
        c=0;p=0; q=0; l=0; m=0; n=6;
        for( p = 0; p < Sistema.N_FILAS; p++ ){
            for( q = 0; q < Sistema.N_COLUMNAS; q++ ){
                if(tablero[q][p] == t){
                    a[c][0]=q;
                    a[c][1]=p;
                    c++;
                }
                if( (tablero[q][p] != 0 && tablero[q][p] != t) || tablero[q][p] == 5 ){
                    c = 0;
                }
                if(c == 3){
                    if(ComRep()==0){
                        System.out.println("horizontal");
                        return 1;//Comer
                    }
                }
            }
            c=0;
        }
        c=0;p=0; q=0; l=0; m=0; n=6;
        for( l = 0; l < Sistema.N_FILAS; l++){
            if(tablero[l][l] == t){
                a[c][0]=l;
                a[c][1]=l;
                c++;
            }
            if( tablero[l][l] == 5 ){
                c = 0;
            }
            if(c == 3){
                if(ComRep()==0){
                    System.out.println("diagonal");
                    return 1;//Comer
                }
            }
        }
        c=0;p=0; q=0; l=0; m=0; n=6;
        for(m = 0; m < Sistema.N_FILAS; m++ ){
            if(tablero[n][m] == t){
                a[c][0]=n;
                a[c][1]=m;
                c++;
            }
            if( tablero[n][m] == 5 ){
                c = 0;
            }
            if(c == 3){
                if(ComRep()==0){
                    System.out.println("diagonal inversa");
                    return 1;//Comer
                }
            }
            n--;
        }
        c=0;p=0; q=0; l=0; m=0; n=6;
        return 0;
    }
    //Verifica s come
    
    
    
    //2fase mover
    /*
    public int moverA(int a, int b, int x, int y, int t){
        int ax=0;
        if (tablero[a][b]==t) {
            
            if (tablero[x][y]==1) {
                tablero[a][b]=1;
                tablero[x][y]=t;
                ax=1;
            }else{
                System.out.println("Posicion Ocupada");
            }
            
        }else{
            System.out.println("No existe ficha en posicion");
        }
        
        return ax;
    }
    */
    public void eliminar_molino(int a, int b){
        int indicador=0;   
        int v=0;
        for(int i=0; i< f.f_e.size(); i++){
            if(a==f.f_e.get(i).cordenada1[0] && a==f.f_e.get(i).cordenada1[1]){
                v=1;
            }
            if(a==f.f_e.get(i).cordenada2[0] && a==f.f_e.get(i).cordenada2[1]){
                v=1;
            }
            if(a==f.f_e.get(i).cordenada3[0] && a==f.f_e.get(i).cordenada3[1]){
                v=1;
            }
            if(v==1){
                f.f_e.remove(i);
                i=0;
            }
            v=0;
        }
        
    }
    public int moverA(int a, int b, int x, int y, int t){
        int x1, x2, y1, y2, vr=0;
        if(a==x){
            y1=Math.max(b,y);//Mayor
            y2=Math.min(b,y);//Menor
            for(int i=y2+1; i<y1; i++){
                if(tablero[a][i]!=0){
                    vr=1;
                }
            }
            if(vr==1){//Hay una ficha de algún tipo de por medio
                return 0;
            }else{
                tablero[x][y]=t;
                tablero[a][b]=1;
                eliminar_molino(a,b);
                return 1;//Sí se puede
            }
        }
        if(b==y){
            x1=Math.max(a,x);//Mayor
            x2=Math.min(a,x);//Menor
            for(int j=x2+1; j<x1; j++){
                if(tablero[b][j]!=0){
                    vr=1;
                }
            }
            if(vr==1){//Hay una ficha de algún tipo de por medio
                return 0;
            }else{
                tablero[x][y]=t;
                tablero[a][b]=1;
                eliminar_molino(a,b);
                return 1;
            }
        }
        if(a!=x && b!=y){
            return 0;//No se puede mover a una posición válida
        }
        return 0;
    }
    public int moverAA(int a, int b, int x, int y, int t){
        int x1, x2, y1, y2, vr=0;
        if(a==x){
            y1=Math.max(b,y);//Mayor
            y2=Math.min(b,y);//Menor
            for(int i=y2+1; i<y1; i++){
                if(tablero[a][i]!=0){
                    vr=1;
                }
            }
            if(vr==1){//Hay una ficha de algún tipo de por medio
                return 0;
            }else{
                tablero[x][y]=t;
                tablero[a][b]=1;
                return 1;//Sí se puede
            }
        }
        if(b==y){
            x1=Math.max(a,x);//Mayor
            x2=Math.min(a,x);//Menor
            for(int j=x2+1; j<x1; j++){
                if(tablero[b][j]!=0){
                    vr=1;
                }
            }
            if(vr==1){//Hay una ficha de algún tipo de por medio
                return 0;
            }else{
                tablero[x][y]=t;
                tablero[a][b]=1;
                return 1;
            }
        }
        if(a!=x && b!=y){
            return 0;//No se puede mover a una posición válida
        }
        return 0;
    }
    
    
    
    public int ComRep(){
        int indicador=0;       
        for(int i=0; i< f.f_e.size(); i++){
            indicador =0;
            if(a[0][0]== f.f_e.get(i).cordenada1[0]){
                indicador++;
            }
            if(a[0][1]== f.f_e.get(i).cordenada1[1]){
                indicador++;
            }
            if(a[1][0]== f.f_e.get(i).cordenada2[0]){
                indicador++;
            }
            if(a[1][1]== f.f_e.get(i).cordenada2[1]){
                indicador++;
            }
            if(a[2][0]== f.f_e.get(i).cordenada3[0]){
                indicador++;
            }
            if(a[2][1]== f.f_e.get(i).cordenada3[1]){
                indicador++;
            }
            if(indicador==6){
                return 1; //Esa posición de comer tiene que cambiar.
            }
        }
        int [] aux1 = {a[0][0],a[0][1]};
        int [] aux2 = {a[1][0],a[1][1]};
        int [] aux3 = {a[2][0],a[2][1]};
        f.f_e.add(new FiE(aux1,aux2,aux3));
        
        return 0;//Nueva posición para comer por lo que se guarda/Sí se come
    }//Almacenan molinos  , 1 si es molino repetido
    
    
    public void elim_F(int a, int b){
        tablero[a][b]=1;
    }
    
    
    
    public void mostrarTablero(){
        for ( int i = 0; i < 7; i++ ){
            for ( int j = 0; j < 7; j++){
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public int verPosi_Cercana(int fich_maq, int x, int y){
        //Si tiene espacios para moverse
        maq.Reset_p();
        int ab=0;
        //A la derecha
        for(int i =y+1; i <7; i++){
            if(tablero[x][i]!=0 && tablero[x][i]!=fich_maq){
                ab=1;
            }
            if(tablero[x][i]==1){
                maq.x_d=x;
                maq.y_d=i;
                System.out.println("DERECHA ");
                System.out.println(maq.x_d);
                System.out.println(maq.y_d);
                ab=1;
            }
            if(ab==1){
                i=10;
            }
        }ab=0;
        //A la izquierda
        for(int i =y-1; i >= 0; i--){
            if(tablero[x][i]!=0 && tablero[x][i]!=fich_maq){
                ab=1;
            }
            if(tablero[x][i]==1){
                maq.x_i=x;
                maq.y_i=i;
                System.out.println("IZQUIERDA ");
                System.out.println(maq.x_i);
                System.out.println(maq.y_i);
                ab=1;
            }
            if(ab==1){
                i=-1;
            }
        }ab=0;
        //Pa abajo
        for(int i =x+1; i <7; i++){
            if(tablero[i][y]!=0 && tablero[i][y]!=fich_maq){
                ab=1;
            }
            if(tablero[i][y]==1){
                maq.x_b=i;
                maq.y_b=y;
                System.out.println("ABAJO ");
                System.out.println(maq.x_b);
                System.out.println(maq.y_b);
                ab=1;
            }
            if(ab==1){
                i=10;
            }
        }ab=0;
        //Pa arriba
        for(int i =x-1; i >= 0; i--){
            if(tablero[i][y]!=0 && tablero[i][y]!=fich_maq){
                ab=1;
            }
            if(tablero[i][y]==1){
                maq.x_a=i;
                maq.y_a=y;
                System.out.println("ARRIBA ");
                System.out.println(maq.x_a);
                System.out.println(maq.y_a);
                ab=1;
            }
            if(ab==1){
                i=-1;
            }
        }ab=0;
        if(maq.x_a==10 && maq.x_b==10 && maq.x_d==10 && maq.x_i==10){
            return 0;
        }
        return 1;
    }
    
    //MAQUINA
   

    public int Rival_t(int t){
        if(t==3){
            return 2;
        }else{
            return 3;
        }
    }
    
    public int Evaluar_general_1(int x, int y, int t, int cantidad){
        int c_hori = 0, c_vert = 0, c_diag = 0, cont=0;
        int [] x_ = new int [3];
        int [] y_ = new int [3];
        //Primero vemos si en esa fila hay 2 fichas T
        
        //HORIZONTAL
        if(y==3){
            if(x < 3){
                for(int i = 0; i < 3; i++){
                    if(tablero[i][y]==t){
                        c_hori++;//Indica que hay una ficha de T en esa fila de la jugada
                    }
                    if(c_hori==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                }
            }
            if(x > 3){
                for(int i = 4; i < 7; i++){
                    if(tablero[i][y]==t){
                        c_hori++;//Indica que hay una ficha de T en esa fila de la jugada
                    }
                    if(c_hori==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                } 
            }
        }else{
            for(int i = 0; i < 7; i++){
                if(tablero[i][y]==t){
                    c_hori++;//Indica que hay una ficha de T en esa fila de la jugada
                }
                if(c_hori==cantidad){
                    if(cantidad==2){
                        cont++;
                    }
                    if(cantidad==3){
                        if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                            cont++;
                        }
                    }
                    i=7;
                }
            }
        }
        //VERTICAL
        if(x==3){
            if(y < 3){
                for(int i = 0; i < 3; i++){
                    if(tablero[x][i]==t){
                        c_vert++;//Indica que hay una ficha de T en esa fila de la jugada
                    }
                    if(c_vert==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                }
            }
            if(y > 3){
                for(int i = 4; i < 7; i++){
                    if(tablero[x][i]==t){
                        c_vert++;//Indica que hay una ficha de T en esa fila de la jugada
                    }
                    if(c_vert==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                } 
            }
        }else{
            for(int i = 0; i < 7; i++){
                if(tablero[x][i]==t){
                    c_vert++;//Indica que hay una ficha de T en esa fila de la jugada
                }
                if(c_vert==cantidad){
                    if(cantidad==2){
                        cont++;
                    }
                    if(cantidad==3){
                        if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                            cont++;
                        }
                    }
                    i=7;
                }
            }
        }
        //DIAGONALES
        if(x < 3){
            if(y < 3){
                for(int i = 0; i < 3; i++){
                    if(tablero[i][i]==t){
                        c_diag++;
                    }
                    if(c_diag==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                }
            }
            if(y > 3){
                int var = 6;
                for(int i = 0 ; i < 3; i++){
                    if(tablero[i][var]==t){
                        c_diag++;
                    }
                    if(c_diag==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                    var--;
                }
            }
        }
        if(x > 3){
            if(y < 3){
                int var = 2;
                for(int i = 4 ; i < 7; i++){
                    if(tablero[i][var]==t){
                        c_diag++;
                    }
                    if(c_diag==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                    var--;
                }
            }
            if(y > 3){
                for(int i = 4; i < 7; i++){
                    if(tablero[i][i]==t){
                        c_diag++;
                    }
                    if(c_diag==cantidad){
                        if(cantidad==2){
                            cont++;
                        }
                        if(cantidad==3){
                            if(ComRep1(x_[0],y_[0],x_[1],y_[1],x_[2],y_[2])==0){
                                cont++;
                            }
                        }
                        i=7;
                    }
                }
            }
        }
        return cont;
    }
    
    public int ComRep1(int x1, int y1, int x2, int y2, int x3, int y3){
        int indicador=0;       
        for(int i=0; i< f.f_e.size(); i++){
            indicador =0;
            if(x1== f.f_e.get(i).cordenada1[0]){
                indicador++;
            }
            if(y1== f.f_e.get(i).cordenada1[1]){
                indicador++;
            }
            if(x2== f.f_e.get(i).cordenada2[0]){
                indicador++;
            }
            if(y2== f.f_e.get(i).cordenada2[1]){
                indicador++;
            }
            if(x3== f.f_e.get(i).cordenada3[0]){
                indicador++;
            }
            if(y3== f.f_e.get(i).cordenada3[1]){
                indicador++;
            }
            if(indicador==6){
                return 1; //Esa posición de comer tiene que cambiar.
            }
        }
        return 0;//Nueva posición para comer por lo que se guarda/Sí se come
        
    }

    
    public int Evaluar_bloqueo_1(int x, int y, int t){
        int a = Rival_t(t), b;
        //Colocamos la ficha con la que se haría un bloqueo como si fuera una ficha del rival para ver si se forman 3 en linea.
        tablero[x][y]=a;
        b = Evaluar_general_1(x, y, a, 3);
        tablero[x][y]=t;
        return b;//representa la cantidad de molinos evitados.
    }
    
    //MODO FACIL
    public void C_F_F(int fich_maq){
        //Registrar las posiciones disponibles
        int a = 0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tablero[i][j]==1){
                    maq.esp_v_x[a]=i;
                    maq.esp_v_y[a]=j;
                    a++;
                }
            }
        }
        //Colocarlas al azar
        b_col = (int) (Math.random() * (a+1));
        tablero[maq.esp_v_x[b_col]][maq.esp_v_y[b_col]]=fich_maq;
        
    }
    
    public void M_F_F(int fich_maq, int x1, int y1){
        //Registrar las posiciones de las fichas de la maquina
        int  c, v=0;
        
        do{
            c = (int) (Math.random() * 4 );
            if(c==0 && maq.x_d != 10){
                tablero[maq.x_d][maq.y_d]=fich_maq;
                tablero[x1][y1]=1;
                back_x=maq.x_d;
                back_y=maq.y_d;
                v=1;
            }
            if(c==1 && maq.x_i != 10){
                tablero[maq.x_i][maq.y_i]=fich_maq;
                tablero[x1][y1]=1;
                back_x=maq.x_i;
                back_y=maq.y_i;
                v=1;
            }
            if(c==2 && maq.x_b != 10){
                tablero[maq.x_b][maq.y_b]=fich_maq;
                tablero[x1][y1]=1;
                back_x=maq.x_b;
                back_y=maq.y_b;
                v=1;
            }
            if(c==3 && maq.x_a != 10){
                tablero[maq.x_a][maq.y_a]=fich_maq;
                tablero[x1][y1]=1;
                back_x=maq.x_a;
                back_y=maq.y_a;
                v=1;
            }
        }while(v==0);
        
    }
    
    
    public void Com_F_F(int rival){
        //Registrar las posiciones disponibles
        int a = 0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tablero[i][j]==rival){
                    maq.esp_v_x[a]=i;
                    maq.esp_v_y[a]=j;
                    a++;
                }
            }
        }
        //Colocarlas al azar
        b_col = (int) (Math.random() * (a));
        tablero[maq.esp_v_x[b_col]][maq.esp_v_y[b_col]]=1;
    }
    
    /*
    **************************************************************************
    **************************************************************************
    **************************************************************************
    */
    
    //MODO NORMAL:
    
    public void C_F_N(){
        
        
    }
    public void C_F_E(){
        
    }
    
    }

    
