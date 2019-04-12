class VoidFuncExpr extends FuncExpr implements BG {
  public VoidFuncExpr(String i)
  {
    super(i);
  }

  public String toString(int t)
  {
  	return(id + "()");
  }

  public void analysis() throws BaseGrammarException
  {
  	return;
  }

  public FullType getType() throws BaseGrammarException
  {
  	return hash.lookup(id, null);
  }
}

