class NestedMatchedWhile extends WhileMatchedIf implements BG {

	NonIfStmt innerAction;
  public NestedMatchedWhile(WhileBase w, NonWhileStmt n)
  {
    super(w);
    innerAction = n;
  }

  public NestedMatchedWhile(WhileBase w, NestedMatchedWhile n)
  {
    super(w);
    innerAction = n;
  }

  public NestedMatchedWhile(WhileBase w)
  {
    super(w);
  }

  public String toString(int t)
  {
  	return( super.toString(t) + T(t) + "{\n" + innerAction.toString(t + 1) + T(t) + "}\n" );
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
  {
    whileBase.analysis();
    innerAction.analysis(scope + 1, rt);
    hash.leaveScope(scope + 1);
    return false;
  }
}

