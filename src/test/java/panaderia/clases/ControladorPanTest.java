/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AlejandroUcan
 */
public class ControladorPanTest {
    /**
     * Test of leerPanes method, of class ControladorPan.
     */
    @Test
    public void testLeerPanes() {
        System.out.println("leerPanes");
        ControladorPan instance = new ControladorPan();
        ArrayList<Pan> expResult = null;
        ArrayList<Pan> result = instance.leerPanes();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of verificarPanRegistrado method, of class ControladorPan.
     */
    @Test
    public void testVerificarPanRegistrado() {
        System.out.println("verificarPanRegistrado");
        ControladorPan instance = new ControladorPan();
        ArrayList<Pan> listaPanes = instance.leerPanes();
        for (int i = 0; i < listaPanes.size(); i++) {
            System.out.println(listaPanes.get(i).toString());
        }
        int id = 0;
        boolean expResult = false;
        boolean result = instance.verificarPanRegistrado(listaPanes, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of escribirPanes method, of class ControladorPan.
     */
    @Test
    public void testEscribirPanes() {
        System.out.println("escribirPanes");
        ControladorPan instance = new ControladorPan();
        ArrayList<Pan> listaPanes = instance.leerPanes();
        ArrayList<Pan> expResult = listaPanes;
        ArrayList<Pan> result = instance.escribirPanes(listaPanes);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
