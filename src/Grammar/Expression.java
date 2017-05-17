package Grammar;

public class Expression {
  
  protected boolean mParenthesis = false;
  
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
