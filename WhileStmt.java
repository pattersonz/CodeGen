abstract class WhileStmt extends NonIfStmt implements BG {
	WhileBase whileBase;
  public WhileStmt(WhileBase w)
  {
    whileBase = w;
  }

  public String toString(int t)
  {
  	return whileBase.toString(t);
  }
    abstract public void(int scope, Integer sizeBelow) throws Exception;
    
}

