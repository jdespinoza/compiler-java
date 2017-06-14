
package CodigoIntermedio;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class CodigoIntermedio {
    
    public ArrayList<Cuadrupla> CI;
    public Cuadrupla[] CIordenado;
    public String nombre;
    public FileWriter fichero;
    
    public CodigoIntermedio(String nombre){
        CI = new ArrayList<>();
        this.nombre = nombre;
        fichero = null;
    }

    public Cuadrupla[] getCIordenado() {
        return CIordenado;
    }
    
    public void guardarCuadrupla(Cuadrupla c){
        CI.add(c);
    }
    
    public boolean abrirFicheroEscritura(){
        try {
            fichero = new FileWriter(nombre);
            return true;
	} catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
            return false;
	}
    }
    
    public void escribirFichero() throws IOException{
        int aux = CI.size();
        CIordenado = new Cuadrupla[aux];
        generarOrdenamiento(aux);
        for(int i = 0; i < aux; i++){
            if (CIordenado[i].getEtiqueta().equals("ETIQUETA_INICIO")){
                CIordenado[i].setEtiqueta("ETIQUETA");
                CIordenado[i].setDir1(null);
            }else if (CIordenado[i].getEtiqueta().equals("SALTAR_CONDICION2")){
                CIordenado[i].setEtiqueta("SALTAR_CONDICION");
                CIordenado[i].setDir2(null);
            }else if (CIordenado[i].getEtiqueta().equals("SALTAR_CONDICION")){
                CIordenado[i].setDir2(null);
            }else if (CIordenado[i].getEtiqueta().equals("SALTAR_Etiqueta2")){
                CIordenado[i].setEtiqueta("SALTAR_ETIQUETA");
                CIordenado[i].setDir1(null);
            }else if (CIordenado[i].getEtiqueta().equals("FUNCION")){
                CIordenado[i].setDir1(null);
            }
            CIordenado[i].setEtiqueta(CIordenado[i].getEtiqueta().toUpperCase());
            fichero.write(CIordenado[i].toString()+"\n");
        }
    }
    
    public void generarOrdenamiento(int n){
        leerCI();
        completarCIordenado();
        
    }
    
    public void leerCI(){
        int aux;
        for(int i = 0; i < CI.size(); i++){
            if(CI.get(i).getEtiqueta().equals("ETIQUETA_INICIO")){
                aux = Integer.parseInt(CI.get(i).dir1);
                CIordenado[aux] = CI.get(i);
            }else if(CI.get(i).getEtiqueta().equals("SALTAR_CONDICION2")){
                aux = Integer.parseInt(CI.get(i).getDir2());
                CIordenado[aux] = CI.get(i);
            }else if(CI.get(i).getEtiqueta().equals("SALTAR_CONDICION")){
                aux = Integer.parseInt(CI.get(i).getDir2());
                CIordenado[aux] = CI.get(i);
            }else if(CI.get(i).getEtiqueta().equals("SALTAR_Etiqueta2")){
                aux = Integer.parseInt(CI.get(i).getDir1());
                CIordenado[aux] = CI.get(i);
            }else if(CI.get(i).getEtiqueta().equals("FUNCION")){
                aux = Integer.parseInt(CI.get(i).getDir1());
                CIordenado[aux] = CI.get(i);
            }
        }
    }
    
    public void completarCIordenado(){
        int pos = 0;
        int i = 0;
        while(i < CI.size()){
            if(CIordenado[pos] != null){
                pos++;
            }else{
                if (CI.get(i).getEtiqueta().equals("ETIQUETA_INICIO")){
                    i++;
                }else if (CI.get(i).getEtiqueta().equals("SALTAR_CONDICION2")){
                    i++;
                }else if (CI.get(i).getEtiqueta().equals("SALTAR_CONDICION")){
                    i++;
                }else if (CI.get(i).getEtiqueta().equals("SALTAR_Etiqueta2")){
                    i++;
                }else if (CI.get(i).getEtiqueta().equals("FUNCION")){
                    i++;
                }else{
                    CIordenado[pos] = CI.get(i);
                    pos++;
                    i++;
                }
            }
        }
    }
    
    public void cerrarFicheroEscritura() throws IOException{
        escribirFichero();
        try{
            fichero.close();
        }catch(Exception ex){
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }
    
}
