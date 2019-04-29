class VoidFuncStmt extends FuncStmt implements BG {
  public VoidFuncStmt(String i)
  {
    super(i);
  }

  public String toString(int t)
  {
  	return(T(t) + id + "();\n");
  }

  public boolean analysis(int scope, ReturnType rt) throws BaseGrammarException
  {
  	hash.lookup(id, null);
    return false;
  }

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\njsr method_" + id + "\n");
    }
}

