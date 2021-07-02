/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

import java.io.Serializable;

/**
 * Esta Clase es la de pan y en ella se alojan los atributos de la clase así como sus constructores
 * @author Alejandro Canché & Carlos Ucan
 */
public class Pan implements Serializable{
    public int idPan;
    public String nombrePan;
    public float precioPan;
    public int existenciaPan;

    /**
     * Este constructor es el encargado de recibir y asignar los valores a los atributos de pan
     * @param idPan valor de tipo int --> identificador de pan
     * @param nombrePan valor de tipo String --> nombre del pan
     * @param precioPan valor de tipo int --> precio del pan
     * @param existenciaPan valor de tipo int --> exixtencia del pan
     */
    public Pan(int idPan, String nombrePan, float precioPan, int existenciaPan) {
        this.idPan = idPan;
        this.nombrePan = nombrePan;
        this.precioPan = precioPan;
        this.existenciaPan = existenciaPan;
    }
    
    public void setIdPan(int idPan) {
        this.idPan = idPan;
    }
    public void setNombrePan(String nombrePan) {
        this.nombrePan = nombrePan;
    }
    public void setPrecioPan(float precioPan) {
        this.precioPan = precioPan;
    }
    public void setExistenciaPan(int existenciaPan) {
        this.existenciaPan += existenciaPan;
    }
    
    public int getIdPan() {
        return this.idPan;
    }
    public String getNombrePan() {
        return this.nombrePan;
    }
    public float getPrecioPan() {
        return this.precioPan;
    }
    public int getExistenciaPan() {
        return this.existenciaPan;
    }
    
    @Override
    public String toString() {
        return "Pan{" + "idPan=" + idPan + ", nombrePan=" + nombrePan + ", precioPan=" + precioPan + ", existenciaPan=" + existenciaPan + '}';
    }
    
}
