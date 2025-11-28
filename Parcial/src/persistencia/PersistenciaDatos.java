package persistencia;

import cine.model.Cine;
import java.io.*;

public class PersistenciaDatos {

    private static final String ARCHIVO = "cine.ser";

    public static void guardar(Cine cine) {
        try {
            //OOS escritura
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
            oos.writeObject(cine); //Guarda el objeto cine en binario en el archivo cine.ser
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static Cine cargar() {
        try {
            //OIS lectura
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO));
            Cine cine = (Cine) ois.readObject(); //reconstruye el objeto desde el archivo binario
            ois.close();
            System.out.println("Cargado correctamente.");
            return cine;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontro archivo.");
            return new Cine();
        }
    }
}
