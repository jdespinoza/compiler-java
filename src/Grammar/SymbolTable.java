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
        for(int i  = 0; i < list.size();i++){
            if(str.compareTo(list.get(i))==0){
                return true;
            }
        }
        return false;
    }
    
    public void putAssignID(String ID, Expression e){
        boolean x = searchIdList(ID, intList);//busca si esta declarada la variable
        try{
           if(x){//si existe
               //Clasifica cual tipo de expresion en para poder resolverla segun corresponda
               if(e.getExpressionType().compareTo("literal")==0){
                   System.out.println("Literal");
                   assignID.put(ID, ((LiteralExpression)e).getLiteral());
               }
               else{
                   assignID.put(ID, String.valueOf(((BinaryExpression)e).evaluate()));
               }
           }
           else{
               System.out.println("La variable no esta declarada."+ID);
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
