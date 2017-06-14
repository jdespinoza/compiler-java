/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammar;

/**
 *
 * @author Carol
 */
public class Simbolo {
    
    private int cod;
    private String id;
    private int direccion;

    public Simbolo() {
        this.cod = -1;
        this.id = "";
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
    public int getDireccion() {
        return direccion;
    }

}
