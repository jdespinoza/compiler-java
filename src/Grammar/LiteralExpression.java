package Grammar;

public class LiteralExpression extends Expression {
  
  // cadena de digitos devuelta por el escaner.
  private String mLiteral = null;
  private TypeLiteralExpression mType = null;
  
  public LiteralExpression() {
    expressionType = "literal";
  }

  public String toString() {
    if( mLiteral == null ) {
      return "";
    }
    return mLiteral;
  }
  
  public Integer evaluate() {
    
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
  
  public boolean evaluateKeyword (String key, BinaryExpression e){
      boolean res = false;
      switch (key){
          case "if":
              res = evaluateIf(e);
              break;
          case "while":
              res = evaluateWhile(e);
              break;
          //case "putw":
              //res = evaluatePutW(e);
            //  break;
          default:
      }
      return res;
  }
  
  public boolean evaluateIf(BinaryExpression e){
      boolean res;
      Integer valor = e.evaluate();
      if (valor > 0){
          res = true;
      }else{
          res = false;
      }
      return res;
  }
  
  public boolean evaluateWhile(BinaryExpression e){
      boolean res = false;
      Integer value = e.evaluate();
      LiteralExpression aux = new LiteralExpression();
      while(value>0){
          value = e.mLi.evaluate() - 1;
          aux.setLiteral(Integer.toString(value));
          e.mLi = aux;
          value = e.evaluate();
          res=true;
          System.out.println("while");
      }
      return res;
  }
  
 /* public boolean evaluatePutW(BinaryExpression e){
      boolean res = false;
      Integer aux = e.evaluate();
      System.out.print(aux);
      return res;
  }
  */
   public void evaluatePutW(Expression e){
       try{
           BinaryExpression bin = (BinaryExpression)e;
           System.out.print("evaluar1 = " +bin.evaluate()+ "\n");
       }catch(Exception a){
           System.out.print("evaluar2 = " +e.evaluate()+ "\n");
       }
      
  }
   
  public void evaluatePutS(String s){
      System.out.println(s);
  }
  
  public void setIf(){
      setType(TypeLiteralExpression.IF);
  }
  
  public void setType(LiteralExpression.TypeLiteralExpression mTipo) {
    this.mType = mTipo;
  }
  
  public enum TypeLiteralExpression {
    IF
  }
  
}
