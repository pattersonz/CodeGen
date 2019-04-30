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
        //push current ar
	writer.append( "ldx $0002\nphx\n");
	int count = arguments.gen(0);
	//set top as current ar
	writer.append("ldx $0000\nstx $0002\n");
	//increment top to be at the top of arg decls
	writer.append("lda $0000\nclc\nadc #$" + hex(count) + "\n");
	//jmp
	writer.append("jsr method_" + id + "\n" +
		      //set old ar as top
		      "lda $0002\nsta $0000\n"+
		      //pull current ar
		      "ply\nsty $0002\n");
    }
}

