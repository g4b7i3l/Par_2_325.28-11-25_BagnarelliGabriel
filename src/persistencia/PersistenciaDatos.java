package persistencia;

import cine.model.Cine;
import java.io.*;

public class PersistenciaDatos {

    private static final String ARCHIVO = "cine.ser";

    public static void guardar(Cine cine) {
        try {
            //Guarda el objeto cine en binario en el archivo cine.ser
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
            oos.writeObject(cine);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static Cine cargar() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO));
            Cine cine = (Cine) ois.readObject();
            ois.close();
            System.out.println("Cargado correctamente.");
            return cine;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontro archivo.");
            return new Cine();
        }
    }
}
