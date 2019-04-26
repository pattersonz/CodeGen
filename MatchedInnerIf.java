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

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
    {
	ifBase.analysis();
	try {
      boolean ifP = matchedIf.analysis(scope + 1, rt);
      hash.leaveScope(scope + 1);
      try {
        boolean ifE = exteriorMatchedIf.analysis(scope + 1, rt);
        hash.leaveScope(scope + 1);
        return ifP && ifE;
      }
      catch(BaseGrammarException e1)
      {
        e1.prepend("else");
        throw e1;
      }
    }
	catch(BaseGrammarException ex)
    {
      hash.leaveScope(scope + 1);
      ex.prepend(ifBase.toString(0).replace("\n",""));
      throw ex;
    }
    }
    
  public String toString(int t)
  {
  	return (T(t) + ifBase.toString(t) + T(t) + "{\n" + matchedIf.toString(t + 1) + T(t) + "}\n" +
      T(t) + "else\n" + T(t) + "{\n" + exteriorMatchedIf.toString(t + 1) + T(t) + "}\n");
  }

            public void gen(int scope, Integer sizeBelow) throws Exception
    {
	int thisIf = ifCount;
	ifCount++;
	ifBase.gen(thisIf);
	writer.append("if_" + Integer.toString(thisIf) + ":\n");
	matchedIf.gen(scope + 1, sizeBelow);
	hash.leaveScope(scope + 1);
	writer.append("jmp fi_" + Integer.toString(thisIf) + "\n" +
		      "else_" + Integer.toString(thisIf) + ":\n");
	matchedIf.gen(scope + 1, sizeBelow);
	hash.leaveScope(scope + 1);
	writer.append("fi_" + Integer.toString(thisIf) + ":\n");
    }
}

