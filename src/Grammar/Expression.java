package Grammar;

public class Expression {
  
  protected boolean mParentesis = false;
  
  public Integer evaluar() {
    return 0; 
  }

  public void setParentesis(boolean mParentesis) {
    this.mParentesis = mParentesis;
  }

  public boolean isParentesis() {
    return mParentesis;
  }
}
