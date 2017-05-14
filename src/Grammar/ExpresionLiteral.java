package Grammar;

public class ExpresionLiteral extends Expresion {
  
  // cadena de digitos devuelta por el escaner.
  private String mLiteral = null;
  private TipoDeExpresionLiteral mTipo = null;
  
  public ExpresionLiteral() {
  }

  public String toString() {
    if( mLiteral == null ) {
      return "";
    }
    return mLiteral;
  }
  
  public Integer evaluar() {
    
    if( mLiteral == null ) {
      return 0;
    }
    
    try {
      Integer valor = Integer.parseInt( mLiteral );
      return valor;
    } catch (Exception ex) {
      System.out.println("Error el Numero " + mLiteral + " no es de tipo Integer.");
    } finally {
    }

    return 0;
    
  }

  public void setLiteral(String mLiteral) {
    this.mLiteral = mLiteral;
  }

  public String getLiteral() {
    return mLiteral;
  }
  
  public boolean evaluateKeyword (String key, ExpresionBinaria e){
      boolean res = false;
      switch (key){
          case "if":
              res = evaluateIf(e);
              break;
          case "while":
              res = evaluateWhile(e);
              break;
          default:
      }
      return res;
  }
  
  public boolean evaluateIf(ExpresionBinaria e){
      boolean res;
      Integer valor = e.evaluar();
      if (valor > 0){
          res = true;
      }else{
          res = false;
      }
      return res;
  }
  
  public boolean evaluateWhile(ExpresionBinaria e){
      boolean res = false;
      Integer valor = e.evaluar();
      ExpresionLiteral aux = new ExpresionLiteral();
      while(valor>0){
          valor = e.mLi.evaluar() - 1;
          aux.setLiteral(Integer.toString(valor));
          e.mLi = aux;
          valor = e.evaluar();
          res=true;
          System.out.println("while");
      }
      return res;
  }
  
  public void setIf(){
      setTipo(TipoDeExpresionLiteral.IF);
  }
  
  public void setTipo(ExpresionLiteral.TipoDeExpresionLiteral mTipo) {
    this.mTipo = mTipo;
  }
  
  public enum TipoDeExpresionLiteral {
    IF
  }
  
}
