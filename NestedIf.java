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

    
        public void gen(int scope, Integer sizeBelow, int thisIf) throws Exception
    {
	writer.append("if_" + Integer.toString(thisIf) + ":\n");
	ifStatement.gen(scope + 1, sizeBelow);
	writer.append("jmp fi_" + Integer.toString(thisIf) + "\n" +
		      "else_" + Integer.toString(thisIf) + ":\n" +
		      "fi_" + Integer.toString(thisIf) + ":\n");
	hash.leaveScope(scope + 1);
    }
}

