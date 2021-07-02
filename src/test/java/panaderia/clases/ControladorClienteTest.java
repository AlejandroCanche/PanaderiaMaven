/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;


//import de.saxsys.javafx.test.JfxRunner;
//import de.saxsys.javafx.test.TestInJfxThread;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

/**
 *
 * @author SALATIEL
 */
//@RunWith(JfxRunner.class)
public class ControladorClienteTest {
    
    public ControladorClienteTest() {
    }
    
    //@TestInJfxThread
    @org.junit.Test
    public void testGuardarRC() {
        System.out.println("guardarRC");
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        int id = 1;
        String tipo = "Entándar";
        String nombre = "Angel";
        String apellido = "Pomol";
        String calle = "24";
        String numero = "262";
        String colonia = "San José Nabalam";
        String CP = "97700";
        String ciudad = "Tizimin";
        String estado = "Yucatan";
        String telefono = "9861234578";
        ControladorCliente instance = new ControladorCliente();
        boolean expResult = true;
        boolean result = instance.guardarRC(listaClientes, id, tipo, nombre, apellido, calle, numero, colonia, CP, ciudad, estado, telefono);

        assertEquals(expResult, result);
    }
    /*
    @TestInJfxThread
    @org.junit.Test
    public void testGuardarRCError() {
        System.out.println("guardarRCError");
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        int id = 1;
        String tipo = "Entándar";
        String nombre = "Angel";
        String apellido = "Pomol";
        String calle = "24";
        String numero = "262";
        String colonia = "San José Nabalam";
        String CP = "97700";
        String ciudad = "Tizimin";
        String estado = "Yucatan";
        String telefono = "9861234578";
        
        Direccion dir = new Direccion(calle, numero, colonia, CP, ciudad, estado, telefono);//Creación de la dirección
        listaClientes.add(new ClienteEstandar(tipo, id, nombre, apellido, dir, 0));
        
        id = 1;
        tipo = "Entándar";
        nombre = "Salatiel";
        apellido = "Poot";
        calle = "20";
        numero = "654";
        colonia = "San José Nabalam";
        CP = "97700";
        ciudad = "Tizimin";
        estado = "Yucatan";
        telefono = "9865421305";
        
        ControladorCliente instance = new ControladorCliente();
        boolean expResult = true;
        boolean result = instance.guardarRC(listaClientes, id, tipo, nombre, apellido, calle, numero, colonia, CP, ciudad, estado, telefono);
        assertEquals(expResult, result);
    }*/
    
    
    //@TestInJfxThread
    @org.junit.Test
    public void testEscribirClientes() {
        System.out.println("escribirClientes");
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        int id = 1;
        String tipo = "Entándar";
        String nombre = "Angel";
        String apellido = "Pomol";
        String calle = "24";
        String numero = "262";
        String colonia = "San José Nabalam";
        String CP = "97700";
        String ciudad = "Tizimin";
        String estado = "Yucatan";
        String telefono = "9861234578";
        
        Direccion dir = new Direccion(calle, numero, colonia, CP, ciudad, estado, telefono);//Creación de la dirección
        listaClientes.add(new ClienteEstandar(tipo, id, nombre, apellido, dir, 0));
        boolean expResult = true;
        ControladorCliente instance = new ControladorCliente();
        boolean result=instance.escribirClientes(listaClientes);
        assertEquals(expResult, result);
    }
    
    /*
    @TestInJfxThread
    @org.junit.Test
    public void testEscribirClientesError() {
        System.out.println("escribirClientes");
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        boolean expResult = true;
        ControladorCliente instance = new ControladorCliente();
        boolean result=instance.escribirClientes(listaClientes);
        assertEquals(expResult, result);
    }
    */
    
    
    
    //@TestInJfxThread
    @org.junit.Test
    public void testObtenerDatosMejorCliente() {
        System.out.println("obtenerDatosMejorCliente");
        ArrayList<Venta> ventasRango = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();;
        ControladorCliente instance = new ControladorCliente();
        
        ventasRango.add(new Venta("01/07/2021",1,1,"concha",4, (float) 2.500000,10));
        
        int id = 1;
        String tipo = "Entándar";
        String nombre = "Angel";
        String apellido = "Pomol";
        String calle = "24";
        String numero = "262";
        String colonia = "San José Nabalam";
        String CP = "97700";
        String ciudad = "Tizimin";
        String estado = "Yucatan";
        String telefono = "9861234578";
        
        Direccion dir = new Direccion(calle, numero, colonia, CP, ciudad, estado, telefono);//Creación de la dirección
        listaClientes.add(new ClienteEstandar(tipo, id, nombre, apellido, dir, 0));
        
        String expResult = "Angel Pomol";
        String result = instance.obtenerDatosMejorCliente(ventasRango, listaClientes);
        assertEquals(expResult, result);       
    }
    /*
    @TestInJfxThread
    @org.junit.Test
    public void testObtenerDatosMejorClienteError() {
        System.out.println("obtenerDatosMejorCliente");
        ArrayList<Venta> ventasRango = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();;
        ControladorCliente instance = new ControladorCliente();
        
        ventasRango.add(new Venta("01/07/2021",1,1,"concha",4, (float) 2.500000,10));
        
        int id = 1;
        String tipo = "Entándar";
        String nombre = "Juan";
        String apellido = "Gomez";
        String calle = "24";
        String numero = "262";
        String colonia = "San José Nabalam";
        String CP = "97700";
        String ciudad = "Tizimin";
        String estado = "Yucatan";
        String telefono = "9861234578";
        
        Direccion dir = new Direccion(calle, numero, colonia, CP, ciudad, estado, telefono);//Creación de la dirección
        listaClientes.add(new ClienteEstandar(tipo, id, nombre, apellido, dir, 0));
        
        String expResult = "Angel Pomol";
        String result = instance.obtenerDatosMejorCliente(ventasRango, listaClientes);
        assertEquals(expResult, result);       
    }*/
    
    //@TestInJfxThread
    @org.junit.Test
    public void testLeerClientes() {
        System.out.println("leerClientes");
        ControladorCliente instance = new ControladorCliente();
        ArrayList<Cliente> expResult = null;
        ArrayList<Cliente> result = instance.leerClientes();
        assertNotEquals(expResult, result);
    }
    /*
    @TestInJfxThread
    @org.junit.Test
    public void testLeerClientesError() {
        System.out.println("leerClientes");
        ControladorCliente instance = new ControladorCliente();
        ArrayList<Cliente> expResult = null;
        ArrayList<Cliente> result = instance.leerClientes();
        assertEquals(expResult, result);
    }*/
    
}
