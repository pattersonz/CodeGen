class WhileIf extends WhileStmt implements BG {

	IfStmt ifStatement;
  public WhileIf(WhileBase w, IfStmt i)
  {
    super(w);
    ifStatement = i;

  }

  public String toString(int t)
  {
  	return( super.toString(t) + T(t) + "{\n" + ifStatement.toString(t + 1) + T(t) + "}\n" );
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      boolean ret = ifStatement.analysis(scope + 1, rt);
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

