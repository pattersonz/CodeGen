class IfBackendStmt extends UnmatchedIf implements BG {
  NonIfStmt nonIfStatement;
  public IfBackendStmt(NonIfStmt n)
  {
    nonIfStatement = n;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	   nonIfStatement.analysis(scope + 1, rt);
     hash.leaveScope(scope + 1);
     return false;
    }
    
  public String toString(int t)
  {
  	return nonIfStatement.toString(t);
  }
}

