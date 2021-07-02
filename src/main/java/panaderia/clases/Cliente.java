    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.Serializable;

/**
 * Esta Clase es la de cliente y en ella se alojan los atributos de la clase así como sus constructores
 * @author Alejandro Canché & Carlos Ucan
 */
public abstract class Cliente implements Serializable{
    public String tipoCliente;
    public int idCliente;
    public String nombreCliente;
    public String aPaternoCliente;
    protected Direccion direccion;
    public int conchapuntos;
    
    /**
     * Este constructor es el encargado de recibir y asignar los valores a los atributos de cliente
     * @param tipoCliente valor de tipo String --> tipo de cliente registrado
     * @param idCliente valor de tipo int --> identificador del cliente 
     * @param nombreCliente valor de tipo String --> nombre del cliente
     * @param aPaternoCliente valor de tipo String --> apellido paterno cliente
     * @param direccion valor de tipo Direccion -->  dirección del cliente
     * @param conchapuntos valor de tipo int --> conchapuntos acumulados
     */

    public Cliente(String tipoCliente, int idCliente, String nombreCliente, String aPaternoCliente, Direccion direccion, int conchapuntos) {
        this.tipoCliente = tipoCliente;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.aPaternoCliente = aPaternoCliente;
        this.direccion = direccion;
        this.conchapuntos = conchapuntos;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getAPaternoCliente() {
        return aPaternoCliente;
    }

    public void setAPaternoCliente(String aPaternoCliente) {
        this.aPaternoCliente = aPaternoCliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getConchapuntos() {
        return conchapuntos;
    }

    public void setConchapuntos(int conchapuntos) {
        this.conchapuntos += conchapuntos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "tipoCliente=" + tipoCliente + ", idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + ", aPaternoCliente=" + aPaternoCliente + ", direccion=" + direccion + ", conchapuntos=" + conchapuntos + '}';
    }

    public abstract int calcularConchapuntos(float monto);
    public abstract void imprimir();
}
