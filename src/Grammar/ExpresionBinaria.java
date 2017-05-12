package Grammar;

public class ExpresionBinaria extends Expresion {
  
  private Expresion mLi = null;
  private Expresion mLd = null;
  private TipoDeExpresionBinaria mTipo = null;
  
  public ExpresionBinaria() {
  }
  
  public String toString() {
    
    if( mTipo == null || mLi == null || mLd == null ) {
      return "";
    }
    
    String li = mLi.toString();
    String ld = mLd.toString();
    String op = "";
    
    if( mTipo == TipoDeExpresionBinaria.Suma ) {
      op = "+";
    } else if( mTipo == TipoDeExpresionBinaria.Resta ) {
      op = "-";
    } else if( mTipo == TipoDeExpresionBinaria.Multiplicacion ) {
      op = "*";
    } else if( mTipo == TipoDeExpresionBinaria.Division) {
      op = "/";
    }
    
    if( mParentesis ) {
      return "( " + li + " " + op + " " + ld + " )";  
    } else {
      return li + " " + op + " " + ld; 
    }    
    
  }
  
  public Integer evaluar() {
    if( mTipo == null || mLi == null || mLd == null ) {
      return 0;
    }
    
    Integer valI = mLi.evaluar();
    Integer valD = mLd.evaluar();
    
    Integer res = 0;
    
    if( mTipo == TipoDeExpresionBinaria.Suma ) {
      res = valI + valD;      
    } else if( mTipo == TipoDeExpresionBinaria.Resta ) {
      res = valI - valD;
    } else if( mTipo == TipoDeExpresionBinaria.Multiplicacion ) {
      res = valI * valD;
    } else if( mTipo == TipoDeExpresionBinaria.Division) {
      if( valD != 0 )
      {
        res = valI / valD;
      } else {
        //problema con cero
        
      }
    } else if(mTipo == TipoDeExpresionBinaria.Greater) {
        if (valI > valD){
            res = 1;
            System.out.println("Hola: 1");
        } else{
            res = 0;
            System.out.println("Hola: 0");
        }
    }else if(mTipo == TipoDeExpresionBinaria.Less){
        if (valI < valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mTipo == TipoDeExpresionBinaria.Equal){
        if (valI == valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mTipo == TipoDeExpresionBinaria.Different){
        if (valI != valD){
            res = 1;
        } else{
            res = 0;
        }
    }else if(mTipo == TipoDeExpresionBinaria.AND){
        /*if (valI && valD){
            res = 1;
        } else{
            res = 0;
        }*/
    }else if(mTipo == TipoDeExpresionBinaria.OR){
        /*if (valI || valD){
            res = 1;
        } else{
            res = 0;
        }*/
    }
    
    return res;
    
  }

  private void setLados( Expresion pLi , Expresion pLd ) {
    this.mLi = pLi;
    this.mLd = pLd;
  }
  
  public void setSuma( Expresion pLi , Expresion pLd ) {
    setLados(pLi,pLd);
    setTipo(TipoDeExpresionBinaria.Suma);
  }
  
  public void setResta( Expresion pLi , Expresion pLd ) {
    setLados(pLi,pLd);
    setTipo(TipoDeExpresionBinaria.Resta);
  }
  
  public void setMulti( Expresion pLi , Expresion pLd ) {
    setLados(pLi,pLd);
    setTipo(TipoDeExpresionBinaria.Multiplicacion);
  }
  
  public void setDiv( Expresion pLi , Expresion pLd ) {
    setLados(pLi,pLd);
    setTipo(TipoDeExpresionBinaria.Division);
  }
  
  public void setGreater( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.Greater);
  }
  
  public void setLess( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.Less);
  }
  
  public void setEqual( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.Equal);
  }
  
  public void setDifferent( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.Different);
  }
  
  public void setAND( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.AND);
  }
  
  public void setOR( Expresion pLi , Expresion pLd ){
      setLados(pLi, pLd);
      setTipo(TipoDeExpresionBinaria.OR);
  }
  
  public void setLi(Expresion mLi) {
    this.mLi = mLi;
  }

  public Expresion getLi() {
    return mLi;
  }

  public void setLd(Expresion mLd) {
    this.mLd = mLd;
  }

  public Expresion getLd() {
    return mLd;
  }

  public void setTipo(ExpresionBinaria.TipoDeExpresionBinaria mTipo) {
    this.mTipo = mTipo;
  }

  public ExpresionBinaria.TipoDeExpresionBinaria getTipo() {
    return mTipo;
  }

  public enum TipoDeExpresionBinaria {
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
