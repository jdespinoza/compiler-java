package Grammar;

public class Expression {
  
  protected boolean mParenthesis = false;
  protected String expressionType;
  
  public String getExpressionType(){
      return expressionType;
  }
  
  public Integer evaluate() {
    return 0; 
  }

  public void setParenthesis(boolean mParentesis) {
    this.mParenthesis = mParentesis;
  }

  public boolean isParenthesis() {
    return mParenthesis;
  }
}
