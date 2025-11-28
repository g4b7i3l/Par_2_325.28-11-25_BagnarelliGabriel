package cine.controller;

import cine.model.Cine;
import cine.model.Sala;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class SalaController {
    
    @FXML
    private ListView<Sala> listaSalas;
    
    @FXML
    public void entrarSala() {
        Sala salaSeleccionada = listaSalas.getSelectionModel().getSelectedItem();//obtiene la sala seleccionada
        
        if (salaSeleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin seleccionar");
            alert.setHeaderText(null);
            alert.setContentText("Por favor elige una pelicula");
            alert.showAndWait();
            return;
        }
        
        try{
            //carga la pantalla de butacas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/view/ButacasView.fxml"));
            Parent root = loader.load();
            
            ButacasController controller = loader.getController(); 
            controller.setSala(salaSeleccionada); //le pasa al controlador la sala seleccionada
            
            Scene scene = listaSalas.getScene(); //cambia la vista por la nueva
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void initialize(){
        Cine cine = Cine.getInstancia();
        listaSalas.getItems().setAll(cine.getSalas());
    }
    
    public void salir(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cine/view/LoginView.fxml"));
            Parent root = loader.load();
            
            Scene scene = listaSalas.getScene();
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
