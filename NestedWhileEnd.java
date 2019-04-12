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

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      nonWhileStatement.analysis(scope + 1, rt);
      hash.leaveScope(scope + 1);
      return false;
    }
    catch(BaseGrammarException ex)
    {
      hash.leaveScope(scope + 1);
      ex.prepend(whileBase.toString(0).replace("\n",""));
      throw ex;
    }
  }
}

