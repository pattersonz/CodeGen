class MatchedFinalIf extends MatchedIf implements BG {
  NonIfStmt nonIfStatement;
  
  public MatchedFinalIf(NonIfStmt n)
  {
    nonIfStatement = n;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	   return nonIfStatement.analysis(scope, rt);
    }
    
  public String toString(int t)
  {
  	return nonIfStatement.toString(t);
  }
}

