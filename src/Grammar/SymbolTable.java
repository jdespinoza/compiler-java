
package Grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    
    public String printSymbolTable(){
        String out = "";
        int len = intList.size();
        for(int i = 0; i < len; i++){
            out+="| int "+intList.get(i);
            if(assignID.get(intList.get(i).toString()) != null){
                out+=" := "+assignID.get(intList.get(i).toString());
            }
            out+=" |\n----------\n";
        }
        return out;
    }
    
}
