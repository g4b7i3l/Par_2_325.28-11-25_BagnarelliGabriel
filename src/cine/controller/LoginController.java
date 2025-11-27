package cine.controller;

import cine.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import persistencia.PersistenciaDatos;

//FXML Diseno de la pantalla JavaFX
public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField nombreField;

    private Cine cine;

    public void initialize() {
        cine = Cine.getInstancia();
    }

    @FXML
    public void login() {
        /*Cliente cliente = cine.buscarCliente(emailField.getText(), passField.getText());
        if (cliente != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("Que tal " + cliente.getNombre() + " bienvenido!");
            alert.showAndWait(); */

        String email = emailField.getText().trim();
        String pass = passField.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", "Completar campos.");
            return;
        }
        Cliente cliente = cine.buscarCliente(email,pass);

        if (cliente != null) {
            cine.setUsuarioActual(cliente);
            
            mostrarAlerta(Alert.AlertType.INFORMATION, "Correcto", "Bienvenido " + cliente.getNombre());

            //Cambio de pantalla
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/view/SalaView.fxml"));
                Parent root = loader.load();

                emailField.getScene().setRoot(root); //reemplaza la pantalla por SalaView
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", "Email o Clave incorrectos.");
        }
    }

    @FXML
    public void registrar() {
        /*Cliente nuevo = new Cliente(nombreField.getText(), emailField.getText(), passField.getText());
        cine.agregarCliente(nuevo);
        PersistenciaDatos.guardar(cine); */

        String nombre = nombreField.getText().trim();
        String email = emailField.getText();
        String pass = passField.getText();

        // validar campos vacios
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", "Completar todos los campos.");
            return;
        }
        // validar email
        if (!email.contains("@") || !email.endsWith(".com")) {
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", "El Mail debe contener @ y terminar en .com");
            return;
        }

        // validar passw
        if (pass.contains(" ") || pass.length() < 3) {
            mostrarAlerta(Alert.AlertType.ERROR,"ERROR","La clave de contener al menos 3 caracteres y no tener espacios.");
            return;
        }
        
        try{
            Cliente nuevo = new Cliente(nombre, email, pass);
            cine.agregarCliente(nuevo);
            PersistenciaDatos.guardar(cine);
            mostrarAlerta(Alert.AlertType.INFORMATION,"Registro exitoso.","Registrado!");
        } catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.ERROR,"ERROR",e.getMessage()); // Mensaje desde Cine.agregarCliente()
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
