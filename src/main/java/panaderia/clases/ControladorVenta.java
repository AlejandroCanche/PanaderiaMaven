/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author AlejandroUcan
 */
public class ControladorVenta {
        /**
     * Este método permite obtener las ventas del archivo ventas.txt
     */
    public ArrayList<Venta> leerVentas() {
        ArrayList<Venta> listaVentas = new ArrayList<>();

        //Lectura de ventas guardadas
        try (ObjectInputStream oisVenta = new ObjectInputStream(new FileInputStream("src/main/resources/archivos/ventas.txt"))) {
            //Cuando no haya mas objetos saltara la excepcion EOFException
            while (true) {
                Venta venta = (Venta) oisVenta.readObject();
                listaVentas.add(new Venta(venta.getFecha(), venta.getIdCliente(), venta.getIdPan(), venta.getNombrePan(), venta.getCantidadPan(), venta.getPrecioPan(), venta.getMonto()));
            }
        } catch (ClassNotFoundException s) {
            System.out.println("No se encontraron los elementos de tipo Venta");
        } catch (EOFException s) {
            System.out.println("\tSe han leido todas las ventas");
        } catch (IOException s) {
            System.out.println("ERROR");
        }
    
        return listaVentas;
    }

}
