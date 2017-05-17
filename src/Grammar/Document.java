package Grammar;

import java.util.LinkedList;

public class Document {
  
  //mas eficiente en inserciones y eliminaciones
  private LinkedList<Expression> mExpressions = new LinkedList<Expression>();
  
  public Document() {
  }
  
  public void addExpression( Expression pExpression ) {
    //System.out.println("exp = "+pExpression.toString());  
    mExpressions.add(pExpression );
  }
  
  public String printReport() {
    
    StringBuilder res = new StringBuilder();

    for( int i = 0; i < mExpressions.size() ; i++ ) {
      Expression temp = mExpressions.get(i);
//      String hola = temp.toString();
//      System.out.println("HolaHola = " + hola);
      try{
            res.append(temp.toString() + "   :=   " + temp.evaluate() );
            res.append("\n");
      }catch(Exception e){
            //System.err.println("Error");
      }
    }
    
    return res.toString();
      
  }
  
  
}
