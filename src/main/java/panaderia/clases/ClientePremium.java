/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panaderia.clases;

/**
 *
 * @author UMT
 */
public class ClientePremium extends Cliente{

    
    
    public ClientePremium(String tipoCliente, int idCliente, String nombreCliente, String aPaternoCliente, Direccion direccion, int conchapuntos) {
        super(tipoCliente, idCliente, nombreCliente, aPaternoCliente, direccion, conchapuntos);
    }

    @Override
    public String getTipoCliente() {
        return tipoCliente;
    }

    @Override
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String getNombreCliente() {
        return nombreCliente;
    }

    @Override
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getaPaternoCliente() {
        return aPaternoCliente;
    }

    public void setaPaternoCliente(String aPaternoCliente) {
        this.aPaternoCliente = aPaternoCliente;
    }

    @Override
    public Direccion getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public int getConchapuntos() {
        return conchapuntos;
    }

    @Override
    public void setConchapuntos(int conchapuntos) {
        this.conchapuntos += conchapuntos;
    }

    @Override
    public int calcularConchapuntos(float monto) {
        int cp = (int) (monto/10);
        
        return cp;
    }

    @Override
    public void imprimir() {
        System.out.println("\tCliente Premium");
    }
    
}