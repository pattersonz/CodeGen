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

    public void gen(int scope, Integer sizeBelow, String method) throws Exception
    {
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\ntxa\nclc\nadc #$0002\nsta $0000\n");
	arguments.gen(2);
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\njsr method_" + id + "\n");
    }
}

