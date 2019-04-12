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
}

