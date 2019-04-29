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

    public void gen() throws Exception
    {
	
	writer.append("lda $0002\nldx $0000\nsta $0000,x\nstx $0002\njsr method_" + id + "\n");
	writer.append("phx\n");
    }
}

