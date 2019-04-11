class VoidFuncStmt extends FuncStmt implements BG {
  public VoidFuncStmt(String i)
  {
    super(i);
  }

  public String toString(int t)
  {
  	return(T(t) + id + "();\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws Exception
  {
  	hash.lookup(id, null);
    return false;
  }
}

