class ArgFuncStmt extends FuncStmt implements BG {
	Args arguments;
  public ArgFuncStmt(String i, Args a)
  {
    super(i);
    arguments = a;
  }

  public String toString(int t)
  {
  	return(T(t) + id + "(" + arguments.toString(t) + ");\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
    FullType[] args = arguments.getTypes(); 
    hash.lookup(id, args);
    return false;
  }
}

