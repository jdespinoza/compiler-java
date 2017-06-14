/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carol
 */
public class Lista {
    
    public List listaCadena;
    
    public Lista(){
        listaCadena = new ArrayList<>();
    }
 
    public void addCadena(String c){
        listaCadena.add(c);
    }
    
    public int size(){
        return listaCadena.size();
    }
    
    public String getCadena(int i){
        return (String) listaCadena.get(i);
    }
    
}
