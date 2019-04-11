class NestedWhile extends WhileStmt implements BG {

	WhileStmt whileStatement;
  public NestedWhile(WhileBase w, WhileStmt n)
  {
    super(w);
    whileStatement = n;

  }

  public String toString(int t)
  {
  	return( super.toString(t) + T(t) + "{\n" + whileStatement.toString(t + 1) + T(t) + "}\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
    whileBase.analysis();
    whileStatement.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    return false;
  }
}

