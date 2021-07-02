/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.Serializable;

/**
 * Esta Clase es la de dirección y en ella se alojan los atributos de la clase así como sus constructores
 * @author Alejandro Canché & Carlos Ucan
 */
public class Direccion implements Serializable{
    protected String calle;
    protected String numero;
    protected String colonia;
    protected String cp;
    protected String ciudad;
    protected String estado;
    protected String telefono;

    /**
     * Este constructor es el encargado de recibir y asignar los valores a los atributos de dirección
     * @param calle = valor de tipo String --> calle de la dirección
     * @param numero = valor de tipo String --> número de la dirección
     * @param colonia = valor de tipo String --> colonia de la dirección
     * @param cp = valor de tipo String --> código postal de la dirección
     * @param ciudad = valor de tipo String --> ciudad de la dirección
     * @param estado = valor de tipo String --> Estado de la dirección
     * @param telefono  = valor de tipo String --> teléfono de la dirección
     */
   public Direccion(String calle, String numero, String colonia, String cp, String ciudad, String estado, String telefono){
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
   }

    public String getCalle() {
        return this.calle;
    }
   public String getNumero(){
        return this.numero;
   }
   public String getColonia(){
        return this.colonia;
   }
   public String getCp(){
        return this.cp;
   }
   public String getCiudad(){
        return this.ciudad;
   }
   public String getEstado(){
        return this.estado;
   }
   public String getTelefono(){
        return this.telefono;
   }        

    @Override
    public String toString() {
        return "C." + calle + " #" + numero + " col." + colonia + ", CP " + cp + " " + ciudad + ", " + estado + " | Teléfono: " + telefono;
    }
  
}
