class MatchedIfBase extends IfBackend implements BG {
  MatchedIf matchedIf;
  Stmt statement;
  public MatchedIfBase(MatchedIf m, Stmt s)
  {
    matchedIf = m;
    statement = s;
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	boolean ifP = matchedIf.analysis( scope + 1, rt);
  hash.leaveScope(scope + 1);
	boolean ifS = statement.analysis(scope + 1, rt);
  hash.leaveScope(scope + 1);
  return ifP && ifS;
    }
    
  public String toString(int t)
  {
  	return (matchedIf.toString(t) + T(t - 1) + "}\n" + T(t - 1) +
      "else\n" + T(t - 1) + "{\n" + statement.toString(t));
  }

    public void gen(int scope, Integer sizeBelow, int thisIf, String method) throws Exception
    {
	writer.append("if_" + Integer.toString(thisIf) + ":\n");
	matchedIf.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("jmp fi_" + Integer.toString(thisIf) + "\n" +
		      "else_" + Integer.toString(thisIf) + ":\n");
	statement.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("fi_" + Integer.toString(thisIf) + ":\n");
    }
}

