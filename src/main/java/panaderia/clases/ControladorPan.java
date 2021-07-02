/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author AlejandroUcan
 */
public class ControladorPan {
        /**
     * Este método permite obtener los panes del archivo panes.txt
     */
    public ArrayList<Pan> leerPanes() {
        ArrayList<Pan> listaPanes = new ArrayList<>();

        //Lectura de panes guardados
        try (ObjectInputStream oisPan = new ObjectInputStream(new FileInputStream("src/main/resources/archivos/panes.txt"))) {
            //Cuando no haya mas objetos saltara la excepcion EOFException
            
            while (true) {
                Pan pan = (Pan) oisPan.readObject();
                listaPanes.add(new Pan(pan.getIdPan(), pan.getNombrePan(), pan.getPrecioPan(), pan.getExistenciaPan()));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontraron los elementos de tipo Pan");
        } catch (EOFException e) {
            System.out.println("\tSe han leido todos los panes");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        
        return listaPanes;
    }
    
    public boolean verificarPanRegistrado(ArrayList<Pan> listaPanes,int id){
        boolean verificacion=false;
        for (int i = 0; i < listaPanes.size(); i++) {
                    if (listaPanes.get(i).idPan == id) {
                        Alert iD = new Alert(Alert.AlertType.WARNING);
                        iD.setHeaderText("Error de ID");
                        iD.setContentText("El iD ya ha sido registrado");
                        iD.show();
                
                        System.out.println("\tEl iD ya ha sido registrado");
                        verificacion= true;
                    }
                }
        
        return verificacion;
    }

    public ArrayList<Pan> escribirPanes(ArrayList<Pan> listaPanes) {
        try (ObjectOutputStream oosPanes = new ObjectOutputStream(new FileOutputStream("src/main/resources/archivos/panes.txt"))) {
            //Escribimos en un fichero
            for (int i = 0; i < listaPanes.size(); i++) {
                oosPanes.writeObject(listaPanes.get(i));
            }
        } catch (IOException e) {
            System.out.println("Direccion de archivo errónea");
        }
        
        return listaPanes;
    }

}
