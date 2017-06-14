/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoIntermedio;

/**
 *
 * @author Carol
 */
public class Cuadrupla {
    
    public String etiqueta;
    public String dir1;
    public String dir2;
    public String dir3;
    
    public Cuadrupla(String etiqueta, String dir1, String dir2, String dir3){
        this.etiqueta = etiqueta;
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.dir3 = dir3;
    }
    
    @Override
    public String toString(){
        String aux;
        aux = etiqueta + " " + dir1 + " " + dir2 + " " + dir3;
        return aux;
    }
    
     public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDir1() {
        return dir1;
    }

    public void setDir1(String dir1) {
        this.dir1 = dir1;
    }

    public String getDir2() {
        return dir2;
    }

    public void setDir2(String dir2) {
        this.dir2 = dir2;
    }

    public String getDir3() {
        return dir3;
    }

    public void setDir3(String dir3) {
        this.dir3 = dir3;
    }
    
}
