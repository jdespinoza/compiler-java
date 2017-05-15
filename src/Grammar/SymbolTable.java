/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carol
 */
public class SymbolTable {
    
    public List intList;
    public Map<String, String> assignID;
    
    public SymbolTable(){
        intList = new ArrayList<>();
        assignID = new HashMap<String, String>(); //ID, valor
    }
    
    public void addType(String type, String ID){
        switch (type){
            case "int":
                intList.add(ID);
                break;
            default:
                //error
                
        }
        
    }
    
    public void putAssignID(String ID, Expresion e){
        try{
           ExpresionBinaria bin = (ExpresionBinaria)e;
           assignID.put(ID, String.valueOf(bin.evaluar()));
       }catch(Exception a){
           assignID.put(ID, String.valueOf(e.evaluar()));
       }
        
    }
    
    public String getValue(String id){
        return assignID.get(id);
    }
    
}
