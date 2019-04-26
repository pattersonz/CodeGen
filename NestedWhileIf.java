class NestedWhileIf extends NestedMatchedWhileIf implements BG{

	NestedMatchedWhileIf nestedMatchedWhile;
	public NestedWhileIf(WhileBase w, NestedMatchedWhileIf n)
	{
		super(w);
		nestedMatchedWhile = n;
	}

	public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
	{
		whileBase.analysis();
		try {
			nestedMatchedWhile.analysis(scope + 1, rt);
			hash.leaveScope(scope + 1);
			return false;
		}
		catch(BaseGrammarException ex)
		{
			hash.leaveScope(scope + 1);
			ex.prepend(whileBase.toString(0).replace("\n",""));
			throw ex;
		}
	}

 	public String toString(int t)
	{
		return( super.toString(t) + T(t) + "{\n" + nestedMatchedWhile.toString(t + 1) + T(t) + "}\n");
	}

    public void gen(int scope, Integer sizeBelow) throws Exception
    {
	int thisWhile = whileCount;
	whileCount++;
	writer.append("while_" + Integer.toString(thisWhile) + ":\n");
	whileBase.gen(thisWhile);
	nestedMatchedWhile.gen(scope + 1, sizeBelow);
	hash.leaveScope(scope + 1);
	writer.append("jmp while_" + Integer.toString(thisWhile) + "\n" +
		      "elihw_" + Integer.toString(thisWhile) + ":\n");
    }

    
}
