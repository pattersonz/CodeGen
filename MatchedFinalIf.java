class MatchedFinalIf extends MatchedIf implements BG {
  NonIfStmt nonIfStatement;
  
  public MatchedFinalIf(NonIfStmt n)
  {
    nonIfStatement = n;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	   return nonIfStatement.analysis(scope, rt);
    }
    
  public String toString(int t)
  {
  	return nonIfStatement.toString(t);
  }

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	nonIfStatement.gen(scope, sizeBelow, method);
    }
}

