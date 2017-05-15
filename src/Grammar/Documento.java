package Grammar;

import java.util.LinkedList;

public class Documento {
  
  //mas eficiente en inserciones y eliminaciones
  private LinkedList<Expresion> mExpresiones = new LinkedList<Expresion>();
  
  public Documento() {
  }
  
  public void agregarExpresion( Expresion pExpresion ) {
    //System.out.println("exp = "+pExpresion.toString());  
    mExpresiones.add( pExpresion );
  }
  
  public String imprimirReporte() {
    
    StringBuilder res = new StringBuilder();

    for( int i = 0; i < mExpresiones.size() ; i++ ) {
      Expresion temp = mExpresiones.get(i);
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
