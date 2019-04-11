class NestedWhileEnd extends NestedMatchedWhile implements BG {

	NonWhileStmt nonWhileStatement;
  public NestedWhileEnd(WhileBase w, NonWhileStmt n)
  {
    super(w);
    nonWhileStatement = n;
  }

  public String toString(int t)
  {
  	return( super.toString(t) + T(t) +  "{\n" + nonWhileStatement.toString(t + 1) + T(t) + "}\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
    whileBase.analysis();
    nonWhileStatement.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    return false;
  }
}

