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
        //push current ar
	writer.append( "ldx $0002\nphx\n");
	//set top as current ar
	writer.append("ldx $0000\nstx $0002\n");
	//jmp
	writer.append("jsr method_" + id + "\n" +
		      //set old ar as top
		      "lda $0002\nsta $0000\n"+
		      //pull current ar
		      "ply\nsty $0002\n");
    }
}

