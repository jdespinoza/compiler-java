package Grammar;

import java.util.LinkedList;

public class Document {
  
  //mas eficiente en inserciones y eliminaciones
  private LinkedList<Expression> mExpresiones = new LinkedList<Expression>();
  
  public Document() {
  }
  
  public void agregarExpresion( Expression pExpresion ) {
    //System.out.println("exp = "+pExpresion.toString());  
    mExpresiones.add( pExpresion );
  }
  
  public String imprimirReporte() {
    
    StringBuilder res = new StringBuilder();

    for( int i = 0; i < mExpresiones.size() ; i++ ) {
      Expression temp = mExpresiones.get(i);
//      String hola = temp.toString();
//      System.out.println("HolaHola = " + hola);
      try{
            res.append(temp.toString() + "   :=   " + temp.evaluar() );
            res.append("\n");
      }catch(Exception excepcion){
            //System.err.println("Error");
      }
    }
    
    return res.toString();
      
  }
  
  
}
