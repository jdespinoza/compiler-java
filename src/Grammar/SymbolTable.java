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
                System.out.println("Agregados a lista "+ID);
                break;
            default:
                //error
                
        }
        
    }
    
    private boolean searchIdList(String str, List l){
        ArrayList<String> list = (ArrayList<String>)l;
        for(int i  = 0; i < l.size();i++){
            if(str.compareTo(l.get(i).toString())==0){
                return true;
            }
        }
        return false;
    }
    public void putAssignID(String ID, Expression e){
        System.out.println("La variable no esta declarada.");
        boolean x = searchIdList(ID, intList);
        try{
           
           //busca si esta declarada la variable
           
            System.out.println("declarada "+x);
           if(searchIdList(ID, intList)){
               
               System.out.println("Strin gagregacion "+ID);
               BinaryExpression bin = (BinaryExpression)e;
               assignID.put(ID, String.valueOf(bin.evaluate()));
           }
           else{
               System.out.println("La variable no esta declarada.");
           }
           
       }catch(Exception a){
           System.err.println("La variable no esta declarada.");
           //assignID.put(ID, String.valueOf(e.evaluate()));
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
