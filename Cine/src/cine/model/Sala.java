
package cine.model;

import java.io.Serializable;


public class Sala implements Serializable{
    private int numero;
    private String pelicula;
    private Butaca[][] butacas;
    private int filas;
    private int columnas;
    
    //+-
    public Sala(int numero, String pelicula,int filas, int columnas) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.filas = filas;
        this.columnas = columnas;
        
        
        butacas = new Butaca[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                butacas[i][j] = new Butaca(i,j);
            }
        }
    }
    //+-

    public int getNumero() {
        return numero;
    }

    public String getPelicula() {
        return pelicula;
    }

    public Butaca[][] getButacas() {
        return butacas;
    }
    
    public int getFilas(){
        return filas;
    }
    
    public int getColumnas(){
        return columnas;
    }
    
    public Butaca getButaca(int fila, int col){
        return butacas[fila][col];
    }
    
    @Override
    public String toString(){
        return pelicula;
    }
}