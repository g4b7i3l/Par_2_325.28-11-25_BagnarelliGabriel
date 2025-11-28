
package cine.model;

import java.io.Serializable;


public class Butaca implements Serializable{
    private int fila;
    private int columna;
    private boolean ocupada;
    private Cliente clienteReservado;

    public Butaca(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupada = false;
        this.clienteReservado = null;
    }
    
    public boolean isOcupada(){
        return ocupada;
    }
    
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }
    
    public Cliente getClienteReservado(){
        return clienteReservado;
    }
    
    public void ocupar(Cliente c){
        ocupada = true;
        this.clienteReservado = c;
    }
    
    public void liberar(){
        this.ocupada = false;
        this.clienteReservado = null;
    }

    public int getFila() {
        return fila;
    }

    public int getcolumna() {
        return columna;
    }
    
    @Override
    public String toString(){
        return "F"+fila+" C"+columna+(ocupada ? " (ocupada)" : "");
    }
    
}