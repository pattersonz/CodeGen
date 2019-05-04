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
		      //get value at AR (return val) and store in x, then move AR to acc
		      "ldy AR\nlda $0000, y\ntax\ntya\n" +
		      //set old ar as top
		      "sta TOP\n"+
		      //pull current ar
		      "ply\nsty AR\n" +
		      //push x for val return
		      "phx\n");
    }
}

