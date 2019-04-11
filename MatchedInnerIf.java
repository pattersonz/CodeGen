class MatchedInnerIf extends MatchedIf implements BG {
  IfBase ifBase;
  MatchedIf matchedIf;
  MatchedIf exteriorMatchedIf;
  public MatchedInnerIf(IfBase i, MatchedIf m, MatchedIf s)
  {
    ifBase = i;
    matchedIf = m;
    exteriorMatchedIf = s;
  }

    public boolean analysis(int scope, ReturnType rt) throws Exception
    {
	ifBase.analysis();
	boolean ifP = matchedIf.analysis(scope + 1, rt);
  hash.leaveScope(scope + 1);
	boolean ifE = exteriorMatchedIf.analysis(scope + 1, rt);
  hash.leaveScope(scope + 1);
  return ifP && ifE;
    }
    
  public String toString(int t)
  {
  	return (T(t) + ifBase.toString(t) + T(t) + "{\n" + matchedIf.toString(t + 1) + T(t) + "}\n" +
      T(t) + "else\n" + T(t) + "{\n" + exteriorMatchedIf.toString(t + 1) + T(t) + "}\n");
  }
}

