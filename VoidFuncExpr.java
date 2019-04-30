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

    public void gen(int extra) throws Exception
    {
        //push current ar
	writer.append( "ldx $0002\nphx\n");
	//set top as current ar
	writer.append("ldx $0000\nstx $0002\n");
	//jmp
	writer.append("jsr method_" + id + "\n" +
		      //set old ar as top
		      "lda $0002\nsta $0000\n"+
		      //pull current ar
		      "ply\nsty $0002\n" +
		      //push x for val return
		      "phx\n");
    }
}

