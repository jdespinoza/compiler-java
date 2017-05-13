/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SymbolTable {
    
    public Map<String, String> declareID;
    public Map<String, String> assignID;
    
    public SymbolTable(){
        declareID = new HashMap<String, String>(); //keyword, ID
        assignID = new HashMap<String, String>(); //ID, valor
    }
    
    public void putDeclareID(String keyword, String ID){
        declareID.put(keyword, ID);
    }
    
    public void putAssignID(String ID, String value){
        assignID.put(ID, value);
    }
    
    public String getValue(String id){
        return assignID.get(id);
    }
    
    public String printSymbolTable(){
        String out = "";
        Iterator t = declareID.entrySet().iterator();
        System.out.println("cantidad elementos "+declareID.size()+" "+assignID.size());
        while(t.hasNext()){
            Map.Entry entry = (Map.Entry)t.next();
            String keyD = entry.getKey().toString();
            String valueD = entry.getValue().toString();
            System.out.println(keyD+" "+valueD);
            //concatena
            out+= "| "+keyD+" "+valueD;
            //si se le asigno un valor
            if(assignID.get(valueD)!= null){
                out+=" := " + assignID.get(valueD);
            }
            out+="\n-----------\n";
        }
        return out;
    }
}
