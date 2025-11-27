package cine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import persistencia.PersistenciaDatos;

public class Cine implements Serializable {

    private List<Sala> salas;
    private List<Entrada> entradas;
    private List<Cliente> clientes;
    private Cliente usuarioActual;

    public Cine() {
        salas = new ArrayList<>();
        entradas = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public void inicializarSalas() {
        if (salas == null || salas.isEmpty()) {
            salas = new ArrayList<>();

            salas.add(new Sala(1, "Null Parte 2", 4, 5));
            salas.add(new Sala(2, "Undefined: el regreso", 5, 6));
            salas.add(new Sala(3, "Volver al ERROR 404", 4, 7));
        }
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public Cliente getUsuarioActual(){
        return usuarioActual;
    }
    
    public void setUsuarioActual(Cliente usuarioActual){
        this.usuarioActual = usuarioActual;
    }

    public Cliente buscarCliente(String email, String pass) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getClave().equals(pass)) {
                return c;
            }
        }
        return null;
    }

    public void agregarCliente(Cliente nuevo) {
        for (Cliente c: clientes){
            if (c.getEmail().equalsIgnoreCase(nuevo.getEmail())){
                throw new IllegalArgumentException("El email ya esta registrado.");
            }
        }
        clientes.add(nuevo);
    }

    public void agregarEntrada(Entrada e) {
        entradas.add(e);
    }
    
    private static Cine instancia;
    public static Cine getInstancia(){
        if (instancia == null){
            instancia = PersistenciaDatos.cargar();
            if (instancia == null){
                instancia = new Cine();
            }
        }
        instancia.inicializarSalas();
        return instancia;
    }

}
