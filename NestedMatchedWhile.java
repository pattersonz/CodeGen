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

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      innerAction.analysis(scope + 1, rt);
      hash.leaveScope(scope + 1);
      return false;
    }
    catch(BaseGrammarException ex)
    {
      hash.leaveScope(scope);
      ex.prepend(whileBase.toString(0).replace("\n",""));
      throw ex;
    }
  }
}

