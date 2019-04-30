class NestedWhileEndIf extends NestedMatchedWhileIf implements BG{
	IfBase ifBase;
	MatchedIf interiorIf;
	MatchedIf otherSideOfElse;

	public NestedWhileEndIf(WhileBase w, IfBase i, MatchedIf m, MatchedIf s)
	{
		super(w);
		ifBase = i;
		interiorIf = m;
		otherSideOfElse = s;
	}

 	public String toString(int t)
	{
		return( super.toString(t) + T(t) + "{\n" + T(t + 1) + ifBase.toString(t + 1) + 
			T(t + 1) + "{\n" + T(t + 2) + interiorIf.toString(t + 2) + T(t + 1) + "}\n"
			+ T(t + 1) + "else\n" + T(t + 1) + "{\n" + otherSideOfElse.toString(t + 2 ) +
			T(t + 1) + "}\n" + T(t) + "}\n");
	}

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
		ifBase.analysis();
		try {
			interiorIf.analysis(scope + 1, rt);
			hash.leaveScope(scope + 1);
			try {
				otherSideOfElse.analysis(scope + 1, rt);
				hash.leaveScope(scope + 1);
				return false;
			}
			catch(BaseGrammarException e2)
			{
				e2.prepend("else");
			}
		}
		catch(BaseGrammarException e1)
		{
			e1.prepend(ifBase.toString(0).replace("\n",""));
			throw e1;
		}
	}
    catch(BaseGrammarException ex)
	{
		hash.leaveScope(scope + 1);
		ex.prepend(whileBase.toString(0).replace("\n",""));
		throw ex;
	}
    return false;
  }

    
    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	int thisWhile = whileCount;
	whileCount++;
	writer.append("while_" + Integer.toString(thisWhile) + ":\n");
	whileBase.gen(thisWhile);
	
	int thisIf = ifCount;
	ifCount++;
	ifBase.gen(thisIf);
	writer.append("if_" + Integer.toString(thisIf) + ":\n");
	interiorIf.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("jmp fi_" + Integer.toString(thisIf) + "\n" +
		      "else_" + Integer.toString(thisIf) + ":\n");
	otherSideOfElse.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("fi_" + Integer.toString(thisIf) + ":\n");
	writer.append("jmp while_" + Integer.toString(thisWhile) + "\n" +
		      "elihw_" + Integer.toString(thisWhile) + ":\n");
    }
}
