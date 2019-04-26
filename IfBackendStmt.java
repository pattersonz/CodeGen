class IfBackendStmt extends UnmatchedIf implements BG {
  NonIfStmt nonIfStatement;
  public IfBackendStmt(NonIfStmt n)
  {
    nonIfStatement = n;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	   nonIfStatement.analysis(scope + 1, rt);
     hash.leaveScope(scope + 1);
     return false;
    }
    
  public String toString(int t)
  {
  	return nonIfStatement.toString(t);
  }

    public void gen(int scope, Integer sizeBelow, int thisIf) throws Exception
    {
	writer.append("if_" + Integer.toString(thisIf) + ":\n");
	nonIfStatement.gen(scope + 1, sizeBelow);
	writer.append("jmp fi_" + Integer.toString(thisIf) + "\n" +
		      "else_" + Integer.toString(thisIf) + ":\n" +
		      "fi_" + Integer.toString(thisIf) + ":\n");
	hash.leaveScope(scope + 1);
    }
}

