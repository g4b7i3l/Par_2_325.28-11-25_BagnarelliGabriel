package cine.controller;

import cine.model.Butaca;
import cine.model.Cine;
import cine.model.Cliente;
import cine.model.Sala;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import persistencia.PersistenciaDatos;

public class ButacasController {

    @FXML
    private Label tituloSala;
    @FXML
    private GridPane grillaButacas;

    private Sala sala;
    private Cine cine;

    public void setSala(Sala sala) {
        this.sala = sala;
        this.cine = Cine.getInstancia();

        tituloSala.setText("Butacas para: " + sala.getPelicula());
        mostrarButacas();
    }

    private void mostrarButacas() {
        grillaButacas.getChildren().clear();

        Cliente usuario = cine.getUsuarioActual();

        for (int fila = 0; fila < sala.getFilas(); fila++) {
            for (int col = 0; col < sala.getColumnas(); col++) {

                Butaca b = sala.getButaca(fila, col);
                Button btn = new Button((fila + 1) + "-" + (col + 1));
                btn.setPrefSize(40, 40);

                if (!b.isOcupada()) {
                    btn.setStyle("-fx-background-color: #00FF00");//verde
                } else if (b.getClienteReservado() != null && b.getClienteReservado().getEmail().equals(usuario.getEmail())) {
                    btn.setStyle("-fx-background-color: #0000FF");//azul
                } else {
                    btn.setStyle("-fx-background-color: #FF0000");//rojo
                    btn.setDisable(true); // para que el u suario no pueda tocar
                }

                int f = fila;
                int c = col;

                btn.setOnAction(e -> reservarButaca(f, c));

                grillaButacas.add(btn, col, fila);
            }
        }
        
    }

    private void reservarButaca(int fila, int col) {
        Butaca b = sala.getButaca(fila, col);
        Cliente usuario = cine.getUsuarioActual();

        if (b.isOcupada()) {

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Cancelar butaca");
            confirm.setHeaderText(null);
            confirm.setContentText("Cancelar butaca " + fila + col + "?");

            Optional<ButtonType> respuesta = confirm.showAndWait();

            if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
                b.liberar();
                PersistenciaDatos.guardar(Cine.getInstancia());
                mostrarButacas();
            }
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar butaca");
        confirm.setHeaderText(null);
        confirm.setContentText("Confirmar butaca " + (fila + 1) + (col + 1) + "?");

        Optional<ButtonType> respuesta = confirm.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            b.ocupar(usuario);
            PersistenciaDatos.guardar(Cine.getInstancia());
            mostrarButacas();
        }

    }

    @FXML
    public void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/view/SalaView.fxml"));
            Parent root = loader.load();

            Scene scene = tituloSala.getScene();
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
