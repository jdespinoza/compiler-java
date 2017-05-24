package Grammar;

public class BinaryExpression extends Expression {
  
  public Expression mLi = null;
  public Expression mLd = null;
  private TypeBinaryExpression mType = null;
  
  public BinaryExpression() {
      expressionType = "binary";
  }
  
  public String toString() {
    
    if( mType == null || mLi == null || mLd == null ) {
      return "";
    }
    
    String li = mLi.toString();
    String ld = mLd.toString();
    String op = "";
    
    if( mType == TypeBinaryExpression.Suma ) {
      op = "+";
    } else if( mType == TypeBinaryExpression.Resta ) {
      op = "-";
    } else if( mType == TypeBinaryExpression.Multiplicacion ) {
      op = "*";
    } else if( mType == TypeBinaryExpression.Division) {
      op = "/";
    } else if( mType == TypeBinaryExpression.AND) {
      op = "&&";
    } else if( mType == TypeBinaryExpression.OR) {
      op = "||";
    } else if( mType == TypeBinaryExpression.Equal) {
      op = "==";
    } else if( mType == TypeBinaryExpression.Different) {
      op = "¡=";
    } else if( mType == TypeBinaryExpression.Greater) {
      op = ">";
    } else if( mType == TypeBinaryExpression.Less) {
      op = "<";
    } 
    
    if( mParenthesis ) {
      return "( " + li + " " + op + " " + ld + " )";  
    } else {
      return li + " " + op + " " + ld; 
    }    
    
  }
  
  public Integer evaluate() {
    if( mType == null || mLi == null || mLd == null ) {
      return 0;
    }
    
    Integer valI = mLi.evaluate();
    Integer valD = mLd.evaluate();
    
    Integer res = 0;
    
    if( mType == TypeBinaryExpression.Suma ) {
      res = valI + valD;      
    } else if( mType == TypeBinaryExpression.Resta ) {
      res = valI - valD;
    } else if( mType == TypeBinaryExpression.Multiplicacion ) {
      res = valI * valD;
    } else if( mType == TypeBinaryExpression.Division) {
      if( valD != 0 )
      {
        res = valI / valD;
      } else {
        //problema con cero
        res = 0;
      }
    } else if(mType == TypeBinaryExpression.Greater) {
        if (valI > valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mType == TypeBinaryExpression.Less){
        if (valI < valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mType == TypeBinaryExpression.Equal){
        if (valI == valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mType == TypeBinaryExpression.Different){
        if (valI != valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mType == TypeBinaryExpression.AND){
        if (valI > 0 && valD > 0){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mType == TypeBinaryExpression.OR){
        if (valI > 0 || valD > 0){
            res = 1;
        } else{
            res = 0;
        }
    }
    
    return res;
    
  }

  private void setSides( Expression pLi , Expression pLd ) {
    this.mLi = pLi;
    this.mLd = pLd;
  }
  
  public void setSum( Expression pLi , Expression pLd ) {
    setSides(pLi,pLd);
    setTipo(TypeBinaryExpression.Suma);
  }
  
  public void setMinus( Expression pLi , Expression pLd ) {
    setSides(pLi,pLd);
    setTipo(TypeBinaryExpression.Resta);
  }
  
  public void setMulti( Expression pLi , Expression pLd ) {
    setSides(pLi,pLd);
    setTipo(TypeBinaryExpression.Multiplicacion);
  }
  
  
  
  public void setDiv( Expression pLi , Expression pLd ) {
    setSides(pLi,pLd);
    setTipo(TypeBinaryExpression.Division);
  }
  
  public void setGreater( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.Greater);
  }
  
  public void setLess( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.Less);
  }
  
  public void setEqual( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.Equal);
  }
  
  public void setDifferent( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.Different);
  }
  
  public void setAND( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.AND);
  }
  
  public void setOR( Expression pLi , Expression pLd ){
      setSides(pLi, pLd);
      setTipo(TypeBinaryExpression.OR);
  }
  
  public void setLi(Expression mLi) {
    this.mLi = mLi;
  }

  public Expression getLi() {
    return mLi;
  }

  public void setLd(Expression mLd) {
    this.mLd = mLd;
  }

  public Expression getLd() {
    return mLd;
  }

  public void setTipo(BinaryExpression.TypeBinaryExpression mTipo) {
    this.mType = mTipo;
  }

  public BinaryExpression.TypeBinaryExpression getTipo() {
    return mType;
  }

  public enum TypeBinaryExpression {
    Suma,
    Resta,
    Division,
    Multiplicacion,
    Greater,
    Less,
    Equal,
    Different,
    AND,
    OR
  }
  
}
