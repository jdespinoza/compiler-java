
package UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java_cup.runtime.Symbol;


public class CodeToUI {
    private String listLexemas;
    private String tablaSimbolos;
    private String mensajesConsola;

    public CodeToUI() {
    }

    public String getListLexemas() {
        return listLexemas;
    }

    public String getTablaSimbolos() {
        return tablaSimbolos;
    }

    public String getMensajesConsola() {
        return mensajesConsola;
    }
    
    
    public void generaListLexemas(ArrayList<String> list){
        listLexemas = "";
        int i = 0;
        while(i < list.size()){
            listLexemas+=list.get(i)+"\n";
            i++;
        }
        
    }
    
    public void generarTablaSimbolos(ArrayList entero, ArrayList funciones){
        tablaSimbolos = "";
        tablaSimbolos+="TYPE INT\n";
        for(int i = 0;i < entero.size();i++){
            tablaSimbolos+="\t"+entero.get(i)+"\n";
        }
        
        tablaSimbolos+="FUNCTION\n";
        for(int i = 0;i < funciones.size();i++){
            tablaSimbolos+="\t"+funciones.get(i)+"\n";
        }
    }
    
    public void generarMensajeConsola(){
        mensajesConsola = "";
    }
}
