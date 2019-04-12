class NestedIf extends UnmatchedIf implements BG {
  IfStmt ifStatement;
  public NestedIf(IfStmt i)
  {
    ifStatement = i;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	   ifStatement.analysis(scope + 1, rt);
     hash.leaveScope(scope + 1);
     return false;
    }
    
  public String toString(int t)
  {
  	return ifStatement.toString(t);
  }
}

