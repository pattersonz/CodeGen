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

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      whileStatement.analysis(scope + 1, rt);
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

