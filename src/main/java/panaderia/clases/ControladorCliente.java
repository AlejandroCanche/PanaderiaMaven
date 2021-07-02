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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


/**
 *
 * @author SALATIEL
 */
public class ControladorCliente {
    
    //Tab RegistrarCliente
    /**
     * Este método permite registrar un nuevo cliente en la BD
     *
     * @param nuevoRC
     */
    @FXML
    public boolean guardarRC(ArrayList<Cliente> listaClientes,int id,String tipo,String nombre,String apellido,String calle,String numero,
            String colonia,String CP,String ciudad,String estado,String telefono) {
        boolean procesarBandera=true;
        
        System.out.println("valor prueba id:"+id);
        for (int i = 0; i < listaClientes.size(); i++) {
            
            if (listaClientes.get(i).idCliente == id) {
                //Alert iD = new Alert(Alert.AlertType.WARNING);
                //iD.setHeaderText("Error de ID");
                //iD.setContentText("El iD ya ha sido registrado");
                //iD.show();


                System.out.println("\tEl iD ya ha sido registrado");
                procesarBandera = false;//Cliente ya registrado
            }
        }
        
        System.out.println("Encontro valor");
        if (procesarBandera) {


            Direccion dir = new Direccion(calle, numero, colonia, CP, ciudad, estado, telefono);//Creación de la dirección

            if (tipo.equals("Estándar")) {
                listaClientes.add(new ClienteEstandar(tipo, id, nombre, apellido, dir, 0));//Creación del cliente Estándar

            } else {
                listaClientes.add(new ClientePremium(tipo, id, nombre, apellido, dir, 0));//Creación del cliente Premium
            }

            //Guardar datos de los Clientes en el archivo clientes.txt
            escribirClientes(listaClientes);

            //Alert nCliente = new Alert(Alert.AlertType.INFORMATION);
            //nCliente.setHeaderText("Registrar cliente");
            //nCliente.setContentText("El cliente ha sido registrado");
            //nCliente.show();
            System.out.println("\tCliente registrado");
        } 
        System.out.println("bandera:"+procesarBandera);
        return procesarBandera;
    }
    
    public boolean escribirClientes(ArrayList<Cliente> listaClientes) {
        boolean exitoEscribirCliente=false;
        try (ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("src/main/resources/archivos/clientes.txt"))) {
            //Escribimos en un fichero
            for (int i = 0; i < listaClientes.size(); i++) {
                exitoEscribirCliente=true;
                oosClientes.writeObject(listaClientes.get(i));
            }
            
            
        } catch (IOException e) {
            System.out.println("\tDireccion de archivo errónea");
        }
        return exitoEscribirCliente;
    }
    
    public String obtenerDatosMejorCliente(ArrayList<Venta> ventasRango,ArrayList<Cliente> listaClientes){
        double trono = 0; //Permite conocer el total de las compras realizadas
        String mejorCliente = "";
        int numClientes = listaClientes.size();//Permite saber cuántos clientes se evaluarán
        int indexCliente = 0;//Define el index del cliente evaluado


        while (numClientes > 0) {
            float total = 0;

            int idCliente = listaClientes.get(indexCliente).idCliente;
            String nombreCliente = listaClientes.get(indexCliente).nombreCliente + " " + listaClientes.get(indexCliente).aPaternoCliente;

            for (int i = 0; i < ventasRango.size(); i++) {
                if (ventasRango.get(i).idCliente == idCliente) {
                    total = total + ventasRango.get(i).monto;
                }
            }

            numClientes--;//Se terminó la consulta del cliente

            //Se evalua si la monto total de las compras realizadas por el cliente es mayor al del cliente anterior 
            if (total > trono) {
                trono = total;
                mejorCliente = nombreCliente;
            }

            indexCliente++;//Se selecciona el siguiente cliente 
        }
        
        return mejorCliente;
    }
    
    public ArrayList<Cliente> leerClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        //Lectura de clientes guardados
        try (ObjectInputStream oisCliente = new ObjectInputStream(new FileInputStream("src/main/resources/archivos/clientes.txt"))) {
            //Cuando no haya más objetos saltará la excepcion EOFException
            while (true) {
                Cliente cliente = (Cliente) oisCliente.readObject();
                
                if(cliente.tipoCliente.equals("Estándar")){
                    listaClientes.add(new ClienteEstandar(cliente.getTipoCliente(), cliente.getIdCliente(), cliente.getNombreCliente(), cliente.getAPaternoCliente(), cliente.getDireccion(), cliente.getConchapuntos()));
                }else{
                    listaClientes.add(new ClientePremium(cliente.getTipoCliente(), cliente.getIdCliente(), cliente.getNombreCliente(), cliente.getAPaternoCliente(), cliente.getDireccion(), cliente.getConchapuntos()));
               
                }
            }
        } catch (ClassNotFoundException f) {
            System.out.println("No se encontraron los elementos de tipo Cliente");
        } catch (EOFException f) {
            System.out.println("\tSe han leido todos los clientes");
        } catch (IOException f) {
            System.out.println("ERROR Cliente");
        }
        
        return listaClientes;
    }
}
