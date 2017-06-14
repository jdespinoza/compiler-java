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
    
    /*public void putAssignID(String ID, Expression e){
        try{
           BinaryExpression bin = (BinaryExpression)e;
           assignID.put(ID, String.valueOf(bin.evaluar()));
       }catch(Exception a){
           assignID.put(ID, String.valueOf(e.evaluar()));
       }
        
    }*/
    
    public String getValue(String id){
        return assignID.get(id);
    }
    public boolean checkID(String type, String ID){
        boolean check = false;
        switch (type){
            case "int":
                check = checkInt(ID);
                break;
            default:
                //error
                
        }
        return check;
    }
    
    
    public boolean checkInt(String ID){
        for(int i = 0; i<intList.size(); i++){
            if(ID.equals(intList.get(i))){
                return true;
            }
        }
        return false;
    }
    
    public void setSimbolo(Simbolo s){
        //int cod = s.getCod();
       
    }
    
}
