package Grammar;

public class ExpresionLiteral extends Expresion {
  
  // cadena de digitos devuelta por el escaner.
  private String mLiteral = null;
  
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
  
}
