class ArgFuncExpr extends FuncExpr implements BG {
	Args arguments;
  public ArgFuncExpr(String i, Args a)
  {
    super(i);
    arguments = a;
  }

  public String toString(int t)
  {
  	return(id + "(" + arguments.toString(t) + ")");
  }

  public void analysis() throws BaseGrammarException
  {
    arguments.analysis();
  }

  public FullType getType() throws BaseGrammarException
  {
    FullType[] args = arguments.getTypes(); 
    return hash.lookup(id, args);
  }
}

