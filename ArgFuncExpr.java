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

    public void gen() throws Exception
    {
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\ntxa\nclc\nadc #$0002\nsta $0000\n");
	arguments.gen(2);
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\njsr method_" + id + "\n");
	writer.append("phx\n");
    }
}

