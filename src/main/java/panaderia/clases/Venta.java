/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.Serializable;

/**
 * Esta Clase es la de ventay en ella se alojan los atributos de la clase así como sus constructores
 * @author Alejandro Canché & Carlos Ucan
 */
public class Venta implements Serializable {
    public String fecha;
    public int idCliente;
    public int idPan;
    public String nombrePan;
    public int cantidadPan;
    public float precioPan;
    public float monto;
    
    /**
     * Este constructor es el encargado de recibir y asignar los valores a los atributos de la clase venta
     * @param fecha valor de tipo String --> fecha de la venta
     * @param idCliente valor de tipo int --> id el cliente que realizó la compra
     * @param idPan valor de tipo int --> id del pan que se vendio
     * @param nombrePan valor de tipo String --> nombre del pan comprado
     * @param cantidadPan valor de tipo int --> cantidad de piezas de pan compradas
     * @param precioPan valor de tipo float --> precio del pan 
     * @param monto valor de tipo float --> precio de los panes comprados
     */

    
    public Venta(String fecha, int idCliente, int idPan, String nombrePan, int cantidadPan, float precioPan, float monto) {
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idPan = idPan;
        this.nombrePan = nombrePan;
        this.cantidadPan = cantidadPan;
        this.precioPan = precioPan;
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPan() {
        return idPan;
    }

    public void setIdPan(int idPan) {
        this.idPan = idPan;
    }

    public String getNombrePan() {
        return nombrePan;
    }

    public void setNombrePan(String nombrePan) {
        this.nombrePan = nombrePan;
    }

    public int getCantidadPan() {
        return cantidadPan;
    }

    public void setCantidadPan(int cantidadPan) {
        this.cantidadPan = cantidadPan;
    }

    public float getPrecioPan() {
        return precioPan;
    }

    public void setPrecioPan(float precioPan) {
        this.precioPan = precioPan;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Venta{" + "fecha=" + fecha + ", idCliente=" + idCliente + ", idPan=" + idPan + ", nombrePan=" + nombrePan + ", cantidadPan=" + cantidadPan + ", precioPan=" + precioPan + ", monto=" + monto + '}';
    }
       
}

