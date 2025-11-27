package cine.model;

import java.io.Serializable;
import java.util.Objects;

/*
Serializar un objeto es convertirlo en una secuencia de bytes para poder:
Guardar este objeto en un archivo o base de datos
Enviar ese objeto a través de una red
Mantener su estado para recuperarlo más tarde.
Luego, cuando lo necesitamos, podemos deserializarlo, es decir, reconstruir el objeto original desde esos bytes.
 */
public class Cliente implements Serializable {

    private String nombre;
    private String email;
    private String clave;

    public Cliente(String nombre, String email, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return email.equals(cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }*/
}
