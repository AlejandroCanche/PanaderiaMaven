package com.mycompany.panaderiamaven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLPanaderia.fxml"));
        
        //Scene scene = new Scene(root);
        
        
       
        
        scene = new Scene(loadFXML("FXMLPanaderia"));
        stage.setScene(scene);
        stage.setTitle("Panaderia");//Nombre de la ventana principal
        Image icono = new Image("/images/logo.png");//Carga del icono
        stage.getIcons().add(icono);//Icono de la ventana principal
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}