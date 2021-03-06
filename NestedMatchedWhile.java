class NestedMatchedWhile extends WhileMatchedIf implements BG {

	NonIfStmt innerAction;
  public NestedMatchedWhile(WhileBase w, NonWhileStmt n)
  {
    super(w);
    innerAction = n;
  }

  public NestedMatchedWhile(WhileBase w, NestedMatchedWhile n)
  {
    super(w);
    innerAction = n;
  }

  public NestedMatchedWhile(WhileBase w)
  {
    super(w);
  }

  public String toString(int t)
  {
  	return( super.toString(t) + T(t) + "{\n" + innerAction.toString(t + 1) + T(t) + "}\n" );
  }

    public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    whileBase.analysis();
    try {
      innerAction.analysis(scope + 1, rt);
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

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	int thisWhile = whileCount;
	whileCount++;
	writer.append("while_" + Integer.toString(thisWhile) + ":\n");
	whileBase.gen(thisWhile);
	innerAction.gen(scope + 1, sizeBelow, method);
	hash.leaveScope(scope + 1);
	writer.append("jmp while_" + Integer.toString(thisWhile) + "\n" +
		      "elihw_" + Integer.toString(thisWhile) + ":\n");
    }
}

